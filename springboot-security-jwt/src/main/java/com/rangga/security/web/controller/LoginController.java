/**
 * 
 */
package com.rangga.security.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ryo rangga sumagusta
 * 
 * @since Jul 30, 2022
 */
@Controller
public class LoginController {
	
	@RequestMapping("/page/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/home")
	public String home(Model model, Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		model.addAttribute("dataUser", user);
		return "home";
	}
}
