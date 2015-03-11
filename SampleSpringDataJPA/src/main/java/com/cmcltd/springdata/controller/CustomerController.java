package com.cmcltd.springdata.controller;

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
import com.cmcltd.springdata.model.EmailAddress;

@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	

		
	
	@RequestMapping(value = "/customers1", method = RequestMethod.POST)
    public @ResponseBody Customer createCustomer(@RequestBody Customer customer) {
        logger.info("Start createCustomer.");
        return(customerRepository.save(customer));
    }
	
	@RequestMapping(value = "/customers1/{id}", method = RequestMethod.GET)
    public @ResponseBody Customer getCustomer(@PathVariable("id") long id) {
        logger.info("Start getCustomers.");
        return(customerRepository.findOne(id));
    }

	@RequestMapping(value = "/customers1/dummy", method = RequestMethod.POST)
    public @ResponseBody Customer createDummyCustomer() {
        logger.info("Start dummyCustomers.");
        Customer customer = new Customer("Rajesh", "Sinha");
        customer.setEmailAddress(new EmailAddress("rajesh.sinha@cmcltd.com"));
        customer.add(new Address("jumari","tallaiya","Delhi"));;
        Customer savedCustomer = customerRepository.save(customer);
        logger.info("saved dummy customer id is "+ savedCustomer.getId() + " Names are " +
        savedCustomer.getFirstname());
        return(savedCustomer);
    }
	
}
