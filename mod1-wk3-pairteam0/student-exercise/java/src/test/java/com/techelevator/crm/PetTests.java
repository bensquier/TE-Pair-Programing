package com.techelevator.crm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PetTests {
	
	Pet petInfo = new Pet("name", "species");

	@Test
	public void list_returns_vaccinations_as_string() {
		List<String> petVaccinations = new ArrayList<String>();
		petVaccinations.add("Rabies");
		petVaccinations.add("Distemper");
		petVaccinations.add("Parvo");
		petInfo.setVaccinations(petVaccinations);
		Assert.assertEquals(petVaccinations.toString(), petInfo.listVaccinations());
	}
}
