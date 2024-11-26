package com.primalsoup.topo.brasov_route_topo.Controller;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.primalsoup.topo.brasov_route_topo.DTO.RouteForm;
import com.primalsoup.topo.brasov_route_topo.Model.Route;
import com.primalsoup.topo.brasov_route_topo.Service.RouteService;

import jakarta.validation.Valid;

@Controller
public class RouteController {
	private final RouteService routeService;

	public RouteController(RouteService routeService) {
		this.routeService = routeService;
	}

	@GetMapping("/routes")
	public String getAllRoutes(Model model) {
		Collection<Route> routes = routeService.getAllRoutes();
		model.addAttribute("routes", routes);
		model.addAttribute("body", "page-route");

		return "main-layout";
	}

	@GetMapping("/add-route")
	public String getRouteForm(Model model) {
		model.addAttribute("routeForm", new RouteForm());
		model.addAttribute("body", "page-route-form");

		return "main-layout";
	}

	@PostMapping("/add-route")
	public String addRoute(@Valid RouteForm routeForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("body", "page-route-form");

			return "main-layout";
		}

		Route route = new Route(routeForm.getName(), routeForm.getDifficulty());
		routeService.addRoute(route);

		model.addAttribute("routes", routeService.getAllRoutes());
		model.addAttribute("body", "page-route");

		return "main-layout";
	}
}