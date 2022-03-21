package com.cognizant.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognizant.portal.model.Login;
import com.cognizant.portal.service.RegisterService;


@Controller
public class SignupController {

	@Autowired
	private RegisterService registerService;

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("signup") Login login) {
		String response = registerService.registerUserNameAndPassword(login);

		if (response != null) {
			return "redirect:login";
		} else {
			return "redirect:login";
		}

	}

	@GetMapping("/register")
	public String displayLoginPage(Model model,@ModelAttribute("signup") Login login) {
		model.addAttribute("login", login);
		return "signup";
	}
}
