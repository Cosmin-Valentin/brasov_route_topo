package com.primalsoup.topo.brasov_route_topo.Controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("body", "page-home");

		return "main-layout";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("body", "page-login");

		return "main-layout";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.logout();  
	    return "redirect:/";
	}
}
