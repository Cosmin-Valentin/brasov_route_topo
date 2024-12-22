package com.primalsoup.topo.brasov_route_topo.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primalsoup.topo.brasov_route_topo.Model.Route;
import com.primalsoup.topo.brasov_route_topo.Model.Sector;
import com.primalsoup.topo.brasov_route_topo.Model.Zone;
import com.primalsoup.topo.brasov_route_topo.Repository.RouteRepository;
import com.primalsoup.topo.brasov_route_topo.Repository.SectorRepository;
import com.primalsoup.topo.brasov_route_topo.Repository.ZoneRepository;

import jakarta.transaction.Transactional;

@Service
public class RouteService {
	private final RouteRepository routeRepository;
	private final ZoneRepository zoneRepository;
	private final SectorRepository sectorRepository;
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	public RouteService(RouteRepository routeRepository, ZoneRepository zoneRepository,
			SectorRepository sectorRepository) {
		this.routeRepository = routeRepository;
		this.zoneRepository = zoneRepository;
		this.sectorRepository = sectorRepository;
	}

	public Collection<Zone> getAllZones() {
		return (Collection<Zone>) zoneRepository.findAll();
	}

	public Zone getZoneById(Long id) {
		return zoneRepository.findById(id).orElse(null);
	}

	public Sector getSectorById(Long id) {
		return sectorRepository.findById(id).orElse(null);
	}

	public boolean routeExistsInSector(String routeName, String sectorName) {
		return routeRepository.existsByNameAndSectorName(routeName, sectorName);
	}

	public List<Route> getAllRoutesSortedByDate() {
		return routeRepository.findRoutesByCreatedAtDate();
	}
	
	public void deleteRouteById(Long id) {
	    routeRepository.deleteById(id);
	}

	@Transactional
	public void addRoute(String zoneName, String sectorName, Route route) {
		Optional<Zone> zoneOpt = zoneRepository.findByName(zoneName);
		Zone zone = zoneOpt.orElseGet(() -> {
			Zone newZone = new Zone(zoneName);
			zoneRepository.save(newZone);
			return newZone;
		});

		Optional<Sector> sectorOpt = sectorRepository.findByName(sectorName);
		Sector sector = sectorOpt.orElseGet(() -> {
			Sector newSector = new Sector(sectorName);
			sectorRepository.save(newSector);
			return newSector;
		});

		if (!zone.getSectors().contains(sector)) {
			zone.addSector(sector);
		}

		if (!sector.getRoutes().contains(route)) {
			sector.addRoute(route);
		}

		route.setSector(sector);
		routeRepository.save(route);

		zoneRepository.save(zone);
	}
	
	public void saveRouteDrawings(Sector sector, Map<String, Object> routeDrawingData) {
		try {
            String jsonData = objectMapper.writeValueAsString(routeDrawingData);
            sector.setRouteDrawingData(jsonData);  
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing route data", e);
        }
	    
	    sectorRepository.save(sector); 
	}

}