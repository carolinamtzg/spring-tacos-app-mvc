package com.carolina.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.carolina.data.IngredientRepository;
import com.carolina.data.TacoRepository;
import com.carolina.model.Ingredient;
import com.carolina.model.Ingredient.Type;
import com.carolina.model.Order;
import com.carolina.model.Taco;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j // implementacion de herramienta de log
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	@Autowired
	private IngredientRepository ingredientRepo;

	@Autowired
	private TacoRepository tacoRepo;

	@GetMapping
	public String showDesignForm(Model model) {
		// las 2 siguientes lineas son para dar el error de ejemplo.
		//int x = 0;
		//float y = 1/x;
		populateIngredients(model);
		return "design";
	}

	@ModelAttribute(name = "tktn")
	public Taco taco() {
		return new Taco();
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
				.filter(i -> i.getType().equals(type))
				.collect(Collectors.toList());
	}

	@PostMapping
	public String processDesign(@Valid @ModelAttribute(name = "tktn") Taco design, Errors errors, Model model,
			@ModelAttribute Order order) {
		if (errors.hasErrors()) {
			populateIngredients(model);
			return "design";
		}
		Taco saved = tacoRepo.save(design);
		order.addDesign(saved);
		log.info("Designed Taco: " + saved);
		return "redirect:/orders/current";
	}

	public void populateIngredients(Model model) {

		List<Ingredient> ingredients = new ArrayList<>();

		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}
}