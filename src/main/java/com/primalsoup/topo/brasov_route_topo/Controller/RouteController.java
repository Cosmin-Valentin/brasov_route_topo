package com.primalsoup.topo.brasov_route_topo.Controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primalsoup.topo.brasov_route_topo.DTO.RouteDTO;
import com.primalsoup.topo.brasov_route_topo.DTO.RouteForm;
import com.primalsoup.topo.brasov_route_topo.DTO.SectorDTO;
import com.primalsoup.topo.brasov_route_topo.DTO.ZoneDTO;
import com.primalsoup.topo.brasov_route_topo.Model.Route;
import com.primalsoup.topo.brasov_route_topo.Model.Sector;
import com.primalsoup.topo.brasov_route_topo.Model.Zone;
import com.primalsoup.topo.brasov_route_topo.Service.RouteService;

import jakarta.validation.Valid;

@Controller
public class RouteController {
	private final RouteService routeService;
	private final ObjectMapper objectMapper;

	public RouteController(RouteService routeService, ObjectMapper objectMapper) {
		this.routeService = routeService;
		this.objectMapper = objectMapper;
	}

	@GetMapping("/routes")
	public String getLatestRoutes(Model model) {
		List<Route> routes = routeService.getAllRoutesSortedByDate();
		model.addAttribute("routes", routes);
		model.addAttribute("body", "page-latest-routes");

		return "main-layout";
	}

	@GetMapping("/areas/{zoneId}/sectors/{sectorId}")
	public String getRoutesPage(@PathVariable Long zoneId, @PathVariable Long sectorId, Model model) {
		Zone zone = routeService.getZoneById(zoneId);
		Sector sector = routeService.getSectorById(sectorId);

		model.addAttribute("zone", zone);
		model.addAttribute("sector", sector);
		model.addAttribute("routes", sector.getRoutes());
		model.addAttribute("routeDrawingData", sector.getRouteDrawingData());
		model.addAttribute("body", "page-routes");

		return "main-layout";
	}

	@GetMapping("/areas")
	public String getAreasPage(Model model) {
		Collection<Zone> zones = routeService.getAllZones();
		model.addAttribute("zones", zones);
		model.addAttribute("body", "page-areas");

		return "main-layout";
	}

	@GetMapping("/areas/{zoneId}")
	public String getSectorsPage(@PathVariable Long zoneId, Model model) {
		Zone zone = routeService.getZoneById(zoneId);
		model.addAttribute("zone", zone);
		model.addAttribute("body", "page-sectors");

		return "main-layout";
	}

	@GetMapping("/add-route")
	public String getRouteForm(Model model) throws JsonProcessingException {
		populateModelForForm(model, new RouteForm(), null);
		return "main-layout";
	}

	@PostMapping("/add-route")
	public String addRoute(@Valid RouteForm routeForm, @RequestParam("sectorImage") MultipartFile sectorImage, BindingResult result, Model model) throws JsonProcessingException {
		boolean routeExists = routeService.routeExistsInSector(routeForm.getName(), routeForm.getSector());

		if (result.hasErrors() || routeExists) {
			if (routeExists) {
				result.rejectValue("name", "error.routeForm", "Ruta există deja în acest sector.");
			}
			populateModelForForm(model, routeForm, result.getAllErrors());
			
			return "main-layout";
		}

		String sectorName = routeForm.getSector();
		String zoneName = routeForm.getZone();
		String newZone = routeForm.getNewZone();
		String newSector = routeForm.getNewSector();

		if ("new".equals(zoneName) && newZone != null && !newZone.isBlank()) {
			zoneName = newZone;
		}
		if ("new".equals(sectorName) && newSector != null && !newSector.isBlank()) {
			sectorName = newSector;
		}

		Route route = new Route(
				routeForm.getName(), 
				routeForm.getDifficulty(), 
				routeForm.getLength(), 
				routeForm.getQuickDraws(), 
				routeForm.getRating()
		);
		
		try {
	        routeService.addRoute(zoneName, sectorName, route, sectorImage);
	    } catch (IOException e) {
	        model.addAttribute("error", "Failed to save sector image.");
	        return "page-route-form";
	    }

		return "redirect:/add-route";
	}
	
	@PostMapping("/remove-route")
	@ResponseBody
	public ResponseEntity<String> removeRoute(@RequestParam Long id) {
		try {
	        routeService.deleteRouteById(id);
	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	@PostMapping("/areas/{zoneId}/sectors/{sectorId}/saveRouteDrawings")
	public ResponseEntity<Void> saveRouteDrawings(
	    @PathVariable Long zoneId,
	    @PathVariable Long sectorId,
	    @RequestBody Map<String, Object> routeDrawingData) {

	    Sector sector = routeService.getSectorById(sectorId);
	    routeService.saveRouteDrawings(sector, routeDrawingData); 
	    
	    return ResponseEntity.ok().build();
	}
	
	@GetMapping("/sectors/{id}/image")
	public ResponseEntity<byte[]> getSectorImage(@PathVariable Long id) {
	    byte[] image = routeService.getSectorImageById(id);

	    if (image == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG)
	            .body(image);
	}

	private void populateModelForForm(Model model, RouteForm routeForm, List<ObjectError> errors) throws JsonProcessingException {
		Collection<Zone> zones = routeService.getAllZones();
		List<ZoneDTO> zoneDTOs = zones.stream().map(this::convertToZoneDTO).collect(Collectors.toList());

		String jsonData = objectMapper.writeValueAsString(zoneDTOs);
		
		model.addAttribute("zones", zones);
		model.addAttribute("jsonData", jsonData);
		model.addAttribute("routeForm", routeForm);
		model.addAttribute("errors", errors);
		model.addAttribute("body", "page-route-form");
	}

	private ZoneDTO convertToZoneDTO(Zone zone) {
		ZoneDTO zoneDTO = new ZoneDTO(zone.getName());

		zone.getSectors().forEach(sector -> {
			SectorDTO sectorDTO = new SectorDTO(sector.getName());
			sector.getRoutes().forEach(route -> {
				RouteDTO routeDTO = new RouteDTO(route.getName(), route.getDifficulty(), route.getLength(), route.getQuickDraws(), route.getRating());
				sectorDTO.addRoute(routeDTO);
			});
			zoneDTO.addSector(sectorDTO);
		});

		return zoneDTO;
	}
}
