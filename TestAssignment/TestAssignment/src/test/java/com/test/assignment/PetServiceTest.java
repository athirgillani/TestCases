package com.test.assignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.assignment.model.PetEntity;

import io.qameta.allure.Epic;
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
	public void createPetTest() throws JsonProcessingException 
	{
		PetEntity samplePet = petServiceHelper.loadTestPet();		
		String petJson = objectMapper.writeValueAsString(samplePet);
		log.debug(petJson);
		
		PetEntity newObject = petServiceHelper.createPet(petJson);
		
		assertNotNull(newObject);
		assertNotNull(newObject.getId());
	}
	
	@Test
	public void retrievePetTest() throws JsonProcessingException 
	{
		PetEntity samplePet = petServiceHelper.loadTestPet();		
		String petJson = objectMapper.writeValueAsString(samplePet);
		log.debug(petJson);
		
		PetEntity newObject = petServiceHelper.createPet(petJson);
		
		PetEntity retrievedObject = petServiceHelper.retrievePet(newObject.getId());
		
		assertNotNull(retrievedObject);
		assertNotNull(retrievedObject.getId());
		assertEquals(retrievedObject.getName(), samplePet.getName());
	}
	
	@Test
	public void updatePetTest() throws JsonProcessingException 
	{
		PetEntity samplePet = petServiceHelper.loadTestPet();		
		String petJson = objectMapper.writeValueAsString(samplePet);
		log.debug(petJson);
		
		PetEntity newObject = petServiceHelper.createPet(petJson);
		
		String updatedName = "tommy grown";
		String updatedStatus = "sold";
		petServiceHelper.updatePet(newObject.getId(), updatedName, updatedStatus);
		
		PetEntity retrievedObject = petServiceHelper.retrievePet(newObject.getId());
		
		assertNotNull(retrievedObject);
		assertNotNull(retrievedObject.getId());
		assertEquals(retrievedObject.getName(), updatedName);
		assertEquals(retrievedObject.getStatus(), updatedStatus);
	}
	
	@Test
	public void removePetTest() throws JsonProcessingException 
	{
		PetEntity samplePet = petServiceHelper.loadTestPet();		
		String petJson = objectMapper.writeValueAsString(samplePet);
		log.debug(petJson);
		
		PetEntity newObject = petServiceHelper.createPet(petJson);
		
		petServiceHelper.deletePet(newObject.getId());
	}
	
}
