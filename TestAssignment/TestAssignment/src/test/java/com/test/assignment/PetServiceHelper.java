package com.test.assignment;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.assignment.model.CategoryEntity;
import com.test.assignment.model.PetEntity;
import com.test.assignment.model.TagEntity;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class PetServiceHelper {

	private ObjectMapper objectMapper;
	
	private final String baseUrl = "https://petstore.swagger.io/v2/pet";
	
	
	@Step("Creating New Pet")
	public PetEntity createPet(String petJson) throws JsonProcessingException {
		RestAssured.baseURI = baseUrl;
		
		String response = given().body(petJson).header("Content-Type", "application/json").
		
		when().post("").
		
		then().assertThat().statusCode(200).extract().response().asString();
		
		PetEntity pet = objectMapper.readValue(response, PetEntity.class);
		
		return pet;
	}
	
	@Step("Load Test Data for a Pet")
	public PetEntity loadTestPet() {
		
		CategoryEntity category = new CategoryEntity(1l, "Dogs");
		TagEntity tag = new TagEntity(1l, "homie");
		
		PetEntity pet = new PetEntity();
		pet.setName("tomy");
		pet.setStatus("Available");
		pet.getPhotoUrls().add("https://destinationksa.com/wp-content/uploads/2015/09/pet-sitting-pg.jpg");
		pet.setCategory(category);
		pet.getTags().add(tag);
		
		return pet;
	}
}
