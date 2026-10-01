package com.cg.customer.service;

import java.util.List;
import java.util.Optional;
import com.cg.customer.domain.Customers;
import com.cg.customer.domain.CustomersDTO;

public interface CustomerService {
	
	public Customers save(Customers customer);
		
	public Customers update(Long customerid,Customers customer);
	
	public CustomersDTO getCustomerById(Long customerid);
	
	public List<Customers> getAll();
	
	public void deleteCustomerById(Long customerid);
	
	public String getMessageFromBank();
	

}
