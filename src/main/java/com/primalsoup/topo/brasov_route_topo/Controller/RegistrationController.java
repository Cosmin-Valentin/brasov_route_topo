package com.primalsoup.topo.brasov_route_topo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.primalsoup.topo.brasov_route_topo.Service.UserService;

@Controller
public class RegistrationController {
	
	private final UserService userService;
	
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String getRegistrationPage(Model model) {
		model.addAttribute("body", "page-register");
		return "main-layout";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
		System.out.println(1);
		try {
			userService.registerUser(username, password);
			return "redirect:/login";
		} catch(IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("body", "page-register");
			return "main-layout";
		}
	}
}
