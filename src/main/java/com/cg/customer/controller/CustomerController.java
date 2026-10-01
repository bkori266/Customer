package com.cg.customer.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bank.controller.BankController;
import com.cg.bank.domain.BankAccount;
import com.cg.bank.service.BankService;
import com.cg.customer.domain.Customers;
import com.cg.customer.domain.CustomersDTO;
import com.cg.customer.service.CustomerService;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	private static final Logger logger=LoggerFactory.getLogger(CustomerController.class);	
	
	@GetMapping("/hello")
	public String message() {
		logger.info("-----CusomerMicro----Controller Hello method called--------");
		return "Hello from Customer-Microservices";
	}
	
	@GetMapping("/secure")
	public ResponseEntity<String> secure() {
	
	return ResponseEntity.ok("JWt verified");
	}
	
	@PostMapping("/save")
	public ResponseEntity<Customers> save(@RequestBody Customers customer) {
		logger.info("-------CusomerMicro--Controller save method called--------");
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));		
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<Customers> update(@PathVariable Long id, @RequestBody Customers customer) {
		logger.info("---CusomerMicro-----Controller update By id method called--------------");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.update(id,customer));		
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomersDTO> getById(@PathVariable Long customerId) {			
		logger.info("-----CusomerMicro----Controller getById method called--------");	 
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Customers>> getAll() {
		logger.info("--CusomerMicro--Controller----Get all method called");
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteById(@PathVariable Long customerId) {
		logger.warn("--CusomerMicro----Controller Delete deleteById method called");
		customerService.deleteCustomerById(customerId);
		return ResponseEntity.ok("Customer deleted Successfully with id"+customerId);
	}
	
	@GetMapping("/bankMessage")
	public ResponseEntity<String> getMessageFromBank() {
		return ResponseEntity.ok().body(customerService.getMessageFromBank());
	}
	
}
