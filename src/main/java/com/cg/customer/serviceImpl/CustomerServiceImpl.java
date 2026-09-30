package com.cg.customer.serviceImpl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.bank.domain.BankAccount;
import com.cg.customer.domain.Customers;
import com.cg.customer.domain.CustomersDTO;
import com.cg.customer.exception.CustomerNotFoundException;
import com.cg.customer.feign.AccountFeignClient;
import com.cg.customer.repository.CustomerRepository;
import com.cg.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger=LoggerFactory.getLogger(CustomerServiceImpl.class);	

	private final AccountFeignClient accountFeign;	
	
	Customers customer;
	
	public CustomerServiceImpl(AccountFeignClient accountFeign) {
		this.accountFeign=accountFeign;
		customer=new Customers();
	}
	
	@Autowired
	CustomerRepository customerRepository;

//	    @CircuitBreaker(name = "bankService",fallbackMethod = "getBankFallback")
//	    public List<BankAccount> getBankDetails(Long customerId) {
//	        return accountFeign.getBankByCustomerId(customerId);
//	    }
//
//	    public List<BankAccount> getBankFallback(Long customerId,Exception ex) {
//	        System.out.println("Fallback Executed");
//	        return Collections.emptyList();
//	    }
	
/*	@CircuitBreaker(name = "accountService",fallbackMethod = "accountFallback")
    public CustomersDTO getCustomer(Long customerId) {

        Customers customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer Not Found"));

        List<BankAccount> accounts =accountFeign.getBankByCustomerId(customerId);

        return new CustomersDTO(
                customer.getCustomerid(),
                customer.getName(),
                customer.getMobileNumber(),
                customer.getAadharNumber(),
                accounts,
                "Success"
        );
    }

    public CustomersDTO accountFallback(Long customerId,Exception ex) {

        Customers customer = customerRepository
                .findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer Not Found"));

        return new CustomersDTO(
        		customer.getCustomerid(),
                customer.getName(),
                customer.getMobileNumber(),
                customer.getAadharNumber(),
                Collections.emptyList(),
                "Account Service is down"
               
        );
    }
*/	
	@Override
	public Customers save(Customers customer) {		
		logger.info("----CusomerMicro--Service save method called-------");
		 return customerRepository.save(customer);
		
	}

	@Override
//	@Cacheable(key = "#customerid",value="Customer")
	public CustomersDTO getCustomerById(Long customerid) {
		CustomersDTO customerDTO=new CustomersDTO();
		List<BankAccount> account=accountFeign.getBankByCustomerId(customerid);
		customer=customerRepository.findById(customerid).orElseThrow(()->new CustomerNotFoundException("Customer not found with this id:"+customerid));
		
		customerDTO.setCustomerid(customer.getCustomerid());
		customerDTO.setName(customer.getName());
		customerDTO.setMobileNumber(customer.getMobileNumber());
		customerDTO.setAadharNumber(customer.getAadharNumber());
		
		if(account.isEmpty()) {
			logger.info("---CusomerMicro-Service getById called with No bank found-----");
			customerDTO.setAccounts(null);
		}
		else 
		{	logger.info("--CusomerMicro--Service getById  called with bank found----------");
			customerDTO.setAccounts(account);}
		
		return customerDTO;
	}

	@Override
	public List<Customers> getAll() {
		logger.info("---CusomerMicro---Service getAll method called-------");
		return customerRepository.findAll();
	}

	@Override
	public void deleteCustomerById(Long customerid) {
		logger.info("---CusomerMicro---Service deleteCustomerById method called-------");
		customer=customerRepository.findById(customerid).orElseThrow(()->new CustomerNotFoundException("Customer not found with this id:"+customerid));
		customerRepository.deleteById(customerid);

	}

	@Override
	public Customers update(Long customerid,Customers customerSave) {
		customer=customerRepository.findById(customerid).orElseThrow(()->new CustomerNotFoundException("Customer not found with this id:"+customerid));
		if(customer==null) {
			throw new CustomerNotFoundException("Customer not found with this id: "+customerid);
		}
		
		else {
			customer.setName(customerSave.getName());
			customer.setAadharNumber(customerSave.getAadharNumber());
			customer.setMobileNumber(customerSave.getMobileNumber());
		}
		logger.info("---CusomerMicro---Service updateId method called-------");
		return customerRepository.save(customer);
	}

}
