package com.carolina.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.carolina.data.OrderRepository;
import com.carolina.model.Order;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepo;
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		if(errors.hasErrors()) {
			log.info("Orden recibida invalida: "+ order);
			return "orderForm";
		}
		
		orderRepo.save(order);
		sessionStatus.setComplete(); // sirve para cerrar la session
		
		log.info("Orden recibida: "+ order);
		return "redirect:/";
	}
	
}
