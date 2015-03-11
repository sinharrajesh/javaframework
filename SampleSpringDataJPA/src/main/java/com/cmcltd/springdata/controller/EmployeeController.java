package com.cmcltd.springdata.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcltd.springdata.model.Employee;

@Controller

public class EmployeeController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();
	
	  
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
        logger.info("Start getEmployee. ID="+empId);
        return empData.get(empId);
    }
     
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public @ResponseBody List<Employee> getAllEmployees() {
        logger.info("Start getAllEmployees.");
        List<Employee> emps = new ArrayList<Employee>();
        Set<Integer> empIdKeys = empData.keySet();
        for(Integer i : empIdKeys){
            emps.add(empData.get(i));
        }
        return emps;
    }
    
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
        logger.info("Start createEmployee.");
        empData.put(emp.getId(), emp);
        return emp;
    }
    
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
        logger.info("Start deleteEmployee.");
        Employee emp = empData.get(empId);
        empData.remove(empId);
        return emp;
    }
    
    @RequestMapping(value = "/employees/create", method = RequestMethod.GET)
    public @ResponseBody Employee createDummyEmployee() {
        logger.info("Start Dummy create with /employees/create");
        Employee emp = new Employee();
        int id = (int) Calendar.getInstance().getTimeInMillis();
        emp.setId(id);
        emp.setName("Temp Name");
        empData.put(id, emp);
        return emp;
    }
}
