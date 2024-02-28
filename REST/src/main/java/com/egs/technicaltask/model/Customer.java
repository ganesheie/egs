package com.egs.technicaltask.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
	
	
	@Id
	@Column(name = "customerref")
	private Long customerref;
	
	public Long getCustomerref() {
		return customerref;
	}

	public void setCustomerref(Long customerref) {
		this.customerref = customerref;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "customername")
	private String customername;
	
	@Column(name="addressline1")
	private String addressline1;
	
	@Column(name="addressline2")
	private String addressline2;
	
	private String town;
	private String county;
	private String country;
	private String postcode;
	
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)        		
		.append("Customer Ref",customerref)
		.append("Customer Name",customername)
		.append("Address Line1",addressline1)
		.append("Address Line2",addressline2)
		.append("Town",town)
		.append("County",county)
		.append("Country",country)
		.append("Postcode",postcode)
	    .toString();
	}
}
