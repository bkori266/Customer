package com.cg.customer.domain;

import java.util.List;

import com.cg.bank.domain.BankAccount;

import lombok.Builder;

@Builder
public class CustomersDTO {

	private Long customerid;
	private String name;
	private String mobileNumber;
	private String aadharNumber;
	private List<BankAccount> accounts;
	private String message;	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Customers [customerid=" + customerid + ", name=" + name + ", mobileNumber=" + mobileNumber
				+ ", aadharNumber=" + aadharNumber + "]";
	}

	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobile) {
		this.mobileNumber = mobile;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

}
