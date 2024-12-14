package com.primalsoup.topo.brasov_route_topo.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.primalsoup.topo.brasov_route_topo.Model.Route;
import com.primalsoup.topo.brasov_route_topo.Model.Sector;
import com.primalsoup.topo.brasov_route_topo.Model.Zone;
import com.primalsoup.topo.brasov_route_topo.Repository.RouteRepository;
import com.primalsoup.topo.brasov_route_topo.Repository.SectorRepository;
import com.primalsoup.topo.brasov_route_topo.Repository.ZoneRepository;

@Controller
public class DataInitializationController {

    private final ZoneRepository zoneRepository;
    private final SectorRepository sectorRepository;
    private final RouteRepository routeRepository;

    @Value("${data.file.path}")
    private String predefinedDataFilePath;

    public DataInitializationController(ZoneRepository zoneRepository, SectorRepository sectorRepository, RouteRepository routeRepository) {
        this.zoneRepository = zoneRepository;
        this.sectorRepository = sectorRepository;
        this.routeRepository = routeRepository;
    }

    @GetMapping("/populate-db")
    @ResponseBody
    public ResponseEntity<Void> populateDatabase() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new ClassPathResource(predefinedDataFilePath).getFile();
            List<Zone> predefinedZones = objectMapper.readValue(jsonFile, objectMapper.getTypeFactory().constructCollectionType(List.class, Zone.class));

            for (Zone zone : predefinedZones) {
            	zoneRepository.save(zone);
                for (Sector sector : zone.getSectors()) {
                	sector.setZone(zone); 
                    sectorRepository.save(sector);
                    for (Route route : sector.getRoutes()) {
                        route.setSector(sector);
                        routeRepository.save(route);
                    }
                }
            }
            return ResponseEntity.ok().build();

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}