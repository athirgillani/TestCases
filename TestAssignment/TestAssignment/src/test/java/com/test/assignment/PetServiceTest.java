package com.test.assignment;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.assignment.model.PetEntity;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Epic("Pet Service Test Suit")
public class PetServiceTest {

	private ObjectMapper objectMapper;
	
	private PetServiceHelper petServiceHelper;
	
	@Before
	public void init() 
	{
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		petServiceHelper = new PetServiceHelper(objectMapper);
	}
	
	@Test
	@Story("Create a new Pet Object")
	public void createPetTest() throws JsonProcessingException 
	{
		PetEntity pet = petServiceHelper.loadTestPet();		
		String petJson = objectMapper.writeValueAsString(pet);
		log.debug(petJson);
		
		PetEntity newObject = petServiceHelper.createPet(petJson);
		
		assertNotNull(newObject);
		assertNotNull(newObject.getId());
	}
	
}
