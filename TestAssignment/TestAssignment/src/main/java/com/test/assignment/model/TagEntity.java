package com.test.assignment.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity implements Serializable {

	private Long id;
	
	private String name;
}
