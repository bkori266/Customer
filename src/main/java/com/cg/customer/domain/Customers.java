package com.cg.customer.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Customers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerid;
	
	@NotBlank(message="Customer cannot be empty")
	@Size(min = 5,max = 20,message = "Name size should be within 5 to 20 characters")
	private String name;
	
	@Pattern( regexp ="^[9876][0-9]{9}$",message ="Invalid Mobile number given")
	private String mobileNumber;
	
	@Pattern( regexp ="[0-9]{12}$",message ="Invalid Aadhar number given")
	private String aadharNumber;
	
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
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAadharNumber() {
		// TODO Auto-generated method stub
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber=aadharNumber;
		
	}
	

}