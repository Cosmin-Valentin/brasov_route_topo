package com.primalsoup.topo.brasov_route_topo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("body", "page-home");

		return "main-layout";
	}
}
