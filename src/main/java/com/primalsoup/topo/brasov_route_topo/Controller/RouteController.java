package com.primalsoup.topo.brasov_route_topo.Controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primalsoup.topo.brasov_route_topo.DTO.RouteDTO;
import com.primalsoup.topo.brasov_route_topo.DTO.RouteForm;
import com.primalsoup.topo.brasov_route_topo.DTO.SectorDTO;
import com.primalsoup.topo.brasov_route_topo.DTO.ZoneDTO;
import com.primalsoup.topo.brasov_route_topo.Model.Route;
import com.primalsoup.topo.brasov_route_topo.Model.Zone;
import com.primalsoup.topo.brasov_route_topo.Service.RouteService;

import jakarta.validation.Valid;

@Controller
public class RouteController {
	private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/add-route")
    public String getRouteForm(Model model) throws JsonProcessingException {
        Collection<Zone> zones = routeService.getAllZones();
        List<ZoneDTO> zoneDTOs = zones.stream().map(this::convertToZoneDTO).collect(Collectors.toList());

        String jsonData = new ObjectMapper().writeValueAsString(zoneDTOs);

        model.addAttribute("zones", zones);
        model.addAttribute("jsonData", jsonData);  
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

        String zoneName = routeForm.getZone();
        String newZone = routeForm.getNewZone();
        String sectorName = routeForm.getSector();
        String newSector = routeForm.getNewSector();

        if ("new".equals(zoneName) && newZone != null && !newZone.isBlank()) {
            zoneName = newZone;
        }

        if ("new".equals(sectorName) && newSector != null && !newSector.isBlank()) {
            sectorName = newSector;
        }

        Route route = new Route(routeForm.getName(), routeForm.getDifficulty());
        routeService.addRoute(zoneName, sectorName, route);

        model.addAttribute("zones", routeService.getAllZones());
        model.addAttribute("body", "page-route");

        return "main-layout";
    }

    private ZoneDTO convertToZoneDTO(Zone zone) {
        ZoneDTO zoneDTO = new ZoneDTO(zone.getName());

        zone.getSectors().forEach(sector -> {
            SectorDTO sectorDTO = new SectorDTO(sector.getName());
            sector.getRoutes().forEach(route -> {
                RouteDTO routeDTO = new RouteDTO(route.getName(), route.getDifficulty());
                sectorDTO.addRoute(routeDTO);
            });
            zoneDTO.addSector(sectorDTO);
        });

        return zoneDTO;
    }
}
