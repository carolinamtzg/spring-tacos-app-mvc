package com.carolina.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class MiErrorController implements ErrorController {
	
	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping("/error")
	public String handleError(Model model, WebRequest webRequest) {
		//mandar email a dev@urgencias.es
		
		Map<String, Object> errorMap = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));
		model.addAttribute("msg", errorMap.get("message"));
		log.error("Ha ocurrido una Excepcion!!!"+errorMap);
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
