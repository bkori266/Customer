package com.cg.customer.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.bank.domain.BankAccount;

@FeignClient(name="BANKACCOUNT")
public interface AccountFeignClient {
	
	@GetMapping("/v1/account/customer/{customerId}")
	public List<BankAccount> getBankByCustomerId(@PathVariable Long customerId);
	
}
