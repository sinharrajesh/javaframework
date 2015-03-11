package com.cmcltd.springdata.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcltd.springdata.dao.CustomerRepository;
import com.cmcltd.springdata.model.Address;
import com.cmcltd.springdata.model.Customer;


@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@RequestMapping(value = "/customers1/{id}", method = RequestMethod.POST)
    public @ResponseBody Customer uodateWholeCustomer(@RequestBody Customer customer) {
        logger.info("Updating Customer");
        return(customerRepository.save(customer));
    }	
	
	@RequestMapping(value = "/customers1/{id}", method = RequestMethod.PATCH)
    public @ResponseBody Customer uodatePartCustomer(@RequestBody Customer customer) {
        logger.info("Updating PART Customer");
        return(customerRepository.save(customer));
    }	
	
	@RequestMapping(value = "/customers1", method = RequestMethod.POST)
    public @ResponseBody Customer createCustomer(@RequestBody Customer customer) {
        logger.info("Start createCustomer.");
        return(customerRepository.save(customer));
    }
	
	@RequestMapping(value = "/customers1/", method = RequestMethod.GET)
    public @ResponseBody List<Customer> getAllCustomer() {
        logger.info("Start getAllCustomers.");
        return (List<Customer>) (customerRepository.findAll());
    }
	
	@RequestMapping(value = "/customers1/findByFirstName/{name}", method = RequestMethod.GET)
    public @ResponseBody List<Customer> findByFirstName(@PathVariable("name") String name) {
        logger.info("Start findByFirstName");
        return(customerRepository.findByFirstname(name));
    }
	
	@RequestMapping(value = "/customers1/findByLastName/{name}", method = RequestMethod.GET)
    public @ResponseBody List<Customer> findByLastName(@PathVariable("name") String name) {
        logger.info("Start findByLastName");
        return(customerRepository.findByLastname(name));
    }
	
	
	
	@RequestMapping(value = "/customers1/{id}", method = RequestMethod.GET)
    public @ResponseBody Customer getCustomer(@PathVariable("id") long id) {
        logger.info("Start getCustomers.");
        return(customerRepository.findOne(id));
    }

	@RequestMapping(value = "/customers1/dummy", method = RequestMethod.POST)
    public @ResponseBody Customer createDummyCustomer() {
        logger.info("Start dummyCustomers.");
        Customer customer = new Customer("Rajesh", "Sinha", "default@cmcltd.com");
        
        customer.add(new Address("jumari","tallaiya","Delhi"));;
        Customer savedCustomer = customerRepository.save(customer);
        logger.info("saved dummy customer id is "+ savedCustomer.getId() + " Names are " +
        savedCustomer.getFirstname());
        return(savedCustomer);
    }
	
}
