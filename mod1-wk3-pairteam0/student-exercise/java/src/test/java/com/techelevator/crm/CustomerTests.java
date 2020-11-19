package com.techelevator.crm;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTests {

	Customer customerBalance = new Customer("firstName", "lastName");
	
	@Test
	public void customer_balance_returns_30_total_cost_for_services_rendered() {
		Map<String, Double> services = new HashMap<>();
		services.put("Grooming", 10D);
		services.put("Walking", 10D);
		services.put("Bathing", 10D);
		Assert.assertEquals(30D, customerBalance.getBalanceDue(services), 0);
	}
}
