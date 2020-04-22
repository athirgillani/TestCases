package com.test.assignment;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PetServiceTest {

	private PetServiceHelper petServiceHelper;
	
	@Before
	public void init() 
	{
		ObjectMapper objectMapper = new ObjectMapper();
		
		petServiceHelper = new PetServiceHelper(objectMapper);
	}
	
	@Test
	public void createPetTest() throws JsonProcessingException 
	{
		petServiceHelper.createPet();
		
		assertTrue(true);
	}
	
}
