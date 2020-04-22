package com.test.assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.assignment.model.CategoryEntity;
import com.test.assignment.model.PetEntity;
import com.test.assignment.model.TagEntity;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.*;

@Slf4j
@AllArgsConstructor
public class PetServiceHelper {

	private ObjectMapper objectMapper;
	
	private final String baseUrl = "https://petstore.swagger.io/";
	
	@Step("Creating New Pet")
	public void createPet() throws JsonProcessingException 
	{
		PetEntity pet = this.loadTestPet();		
		String petJson = objectMapper.writeValueAsString(pet);
		log.debug(petJson);
		System.out.println(petJson);
		
		RestAssured.baseURI = baseUrl;
		
		given().body(petJson).header("Content-Type", "application/json").
		
		when().post("v2/pet").
		
		then().assertThat().statusCode(200);
	}
	
	@Step("Load Test Data for a Pet")
	public PetEntity loadTestPet() {
		
		CategoryEntity category = new CategoryEntity(1, "Dogs");
		TagEntity tag = new TagEntity(1, "homie");
		
		PetEntity pet = new PetEntity();
		pet.setName("tomy");
		pet.setStatus("Available");
		pet.getPhotoUrls().add("https://destinationksa.com/wp-content/uploads/2015/09/pet-sitting-pg.jpg");
		pet.setCategory(category);
		pet.getTags().add(tag);
		
		return pet;
	}
}
