package com.techelevator.crm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.techelevator.Billable;
import com.techelevator.Person;

public class Customer extends Person implements Billable {

	private String phoneNumber;
	private List<String> pets = new ArrayList<>();
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<String> getPets() {
		return pets;
	}
	public void setPets(List<String> pets) {
		this.pets = pets;
	}
	
	public Customer(String firstName, String lastName, String phoneNumber) {
		super(firstName, lastName);
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public double getBalanceDue(Map<String, Double> servicesRendered) {
		double balance = 0;
		for (Entry<String, Double> service : servicesRendered.entrySet()) {
			balance += service.getValue();
		}
		return balance;
	}
	
	public Customer(String firstName, String lastName) {
		super(firstName, lastName);
		this.phoneNumber = "";
	}
}
