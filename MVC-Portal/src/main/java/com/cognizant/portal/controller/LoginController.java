package com.cognizant.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cognizant.portal.model.Login;
import com.cognizant.portal.service.LoginService;

@SessionAttributes("login")
@Controller
public class LoginController {

	private final LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/login")
	public String displayLoginPage(Model model, Login login) {
		model.addAttribute("login", login);
		return "login";
	}

	@PostMapping("/login")
	public String parseLoginPage(@ModelAttribute("login") Login login) {
		// log.debug("Login Request: {}", login);
		String response = loginService.validateUserNameAndPassword(login);

		if (response != null) {
//	            return "redirect:menu-item?msg=Login Successful..";
			return "redirect:";
		} else {
			return "redirect:login";
		}

	}
}
