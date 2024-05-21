package com.carolina.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Taco {
	
	private Long id;
	private Date createdAt;
	
	@Size(min=5, message="El nombre debe tener al menos 5 caracteres.")
	private String name;
	private List<Ingredient> ingredients;
}
