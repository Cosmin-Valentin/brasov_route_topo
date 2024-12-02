package com.primalsoup.topo.brasov_route_topo.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.primalsoup.topo.brasov_route_topo.Model.Route;
import com.primalsoup.topo.brasov_route_topo.Model.Sector;
import com.primalsoup.topo.brasov_route_topo.Model.Zone;

@Repository
public class RouteRepository {

	private final Map<String, Zone> zoneMap = new HashMap<>();

	public Collection<Zone> findAllZones() {
		return zoneMap.values();
	}

//	public Collection<Sector> findAllSectors() {
//		return zoneMap.ge
//	}

	public void saveRouteToSector(String zoneName, String sectorName, Route route) {
		Zone zone = zoneMap.computeIfAbsent(zoneName, Zone::new);

		Sector sector = zone.getSectors().stream()
                .filter(s -> s.equals(new Sector(sectorName)))
                .findFirst()
                .orElseGet(() -> {
                    Sector newSector = new Sector(sectorName);
                    zone.addSector(newSector);
                    return newSector;
                });
		
		if(!sector.getRoutes().contains(route)) {
			sector.addRoute(route);
		}
		
//		if (zone == null) {
//			zone = new Zone(zoneName);
//			zoneMap.put(zoneName, zone);
//			sector = new Sector(sectorName);
//			sectorMap.put(sectorName, sector);
//			sector.addRoute(route);
//			zone.addSector(sector);
//		} else {
//			if (!zone.getSectors().contains(sector)) {
//				if (sector == null) {
//					sector = new Sector(sectorName);
//					sectorMap.put(sectorName, sector);
//				}
//				sector.addRoute(route);
//				zone.addSector(sector);
//			} else {
//				if (!sector.getRoutes().contains(route)) {
//					sector.addRoute(route);
//				}
//			}
//		}
	}
}
