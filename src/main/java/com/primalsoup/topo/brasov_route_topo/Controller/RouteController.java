package com.primalsoup.topo.brasov_route_topo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

//	@GetMapping("/routes")
//	public String getAllRoutes(Model model) {
//		model.addAttribute("sectors", routeService.getAllSectors());
//		model.addAttribute("body", "page-route");
//
//		return "main-layout";
//	}

	@GetMapping("/add-route")
	public String getRouteForm(Model model) throws JsonProcessingException {
		model.addAttribute("zones", routeService.getAllZones());
		String jsonData = new ObjectMapper().writeValueAsString(routeService.getAllZones());
		System.out.println("json: " + jsonData);
		model.addAttribute("jsonData",jsonData);
		model.addAttribute("routeForm", new RouteForm());
		model.addAttribute("body", "page-route-form");

		return "main-layout";
	}
	
	@PostMapping("/add-route")
	public String addRoute(@Valid RouteForm routeForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("zones", routeService.getAllZones());
			model.addAttribute("body", "page-route-form");

			return "main-layout";
		}

//		System.out.println(routeForm);
		
		String zoneName = routeForm.getZone();
		String newZone = routeForm.getNewZone();
		String sectorName = routeForm.getSector();
		String newSector = routeForm.getNewSector();
		
		if("new".equals(zoneName) && newZone != null && !newZone.isBlank()) {
			zoneName = newZone;
		}

		if ("new".equals(sectorName) && newSector != null && !newSector.isBlank()) {
			sectorName = newSector;
		}

		Route route = new Route(routeForm.getName(), routeForm.getDifficulty());
		routeService.addRouteToSector(zoneName, sectorName, route);
//		System.out.printf("zone: %s; sector: %s; route:%s", zoneName, sectorName, route);

		model.addAttribute("zones", routeService.getAllZones());
		model.addAttribute("body", "page-route");

		return "main-layout";
	}
}
