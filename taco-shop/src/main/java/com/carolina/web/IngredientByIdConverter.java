package com.carolina.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.carolina.data.IngredientRepository;
import com.carolina.model.Ingredient;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
	
	@Autowired
	IngredientRepository ingredientRepo;
	
	@Override
	public Ingredient convert(String source) {
		return ingredientRepo.findOne(source);
	}

}
