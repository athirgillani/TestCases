package com.test.assignment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetEntity implements Serializable {

	private Integer id;
	
	private String name;
	
	private String status;
	
	private List<String> photoUrls = new ArrayList();
	
	private CategoryEntity category;
	
	private List<TagEntity> tags = new ArrayList();
	
}
