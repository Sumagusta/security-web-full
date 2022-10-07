/**
 * 
 */
package com.rangga.security.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ryo rangga sumagusta
 * 
 */
@Controller
public class LoginController {

	@Autowired
	private SessionRegistry sessionRegistry;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/home")
	public String home(Model model, Authentication authentication, HttpServletResponse response) {
		User user = (User) authentication.getPrincipal();

		setDummyCookie(response);
		List<SessionInformation> sessions = sessionRegistry.getAllSessions(authentication.getPrincipal(), false);
		model.addAttribute("currentSession", sessions);
		model.addAttribute("dataUser", user);
		return "home";
	}

	private void setDummyCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("dummyCookie", "dummy_cookie");
		cookie.setMaxAge(2592000);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
