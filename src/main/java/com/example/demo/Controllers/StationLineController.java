package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.Station_LineDto;
import com.example.demo.Services.BusService;
import com.example.demo.Services.Station_LineService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/stationline")

public class StationLineController {
     @Autowired
    public Station_LineService station_LineService;

    @GetMapping("/getall")
    public ResponseEntity<Iterable<Station_LineDto>> getAll() {
        return ResponseEntity.ok(station_LineService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(Station_LineDto station_LineDto) {
        Station_LineDto s=station_LineService.add(station_LineDto);
        System.out.println("______________________________________-"+s.getStationId()+" "+s.getId());
        if (s!=null)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getbyid")
    public ResponseEntity<Station_LineDto> getById(Long id) {
        if (station_LineService.getById(id) != null)
            return ResponseEntity.ok(station_LineService.getById(id));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        station_LineService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("AllStations")
    public ResponseEntity<Iterable<Station_LineDto>> getAllStations(Long lineId) {
        return ResponseEntity.ok(station_LineService.AllStationInLine(lineId));
    }
    @GetMapping("LocationBuses")
    public ResponseEntity<Iterable<Station_LineDto>> LocationBuses(Long LineId) {
        return ResponseEntity.ok(station_LineService.locationOfTheBusesAlongTheEntireRoute(LineId));
    }
        @GetMapping("/getbyStation")
    public ResponseEntity<List<Map<String, Object>>> getBusArrivalsAtStation(@RequestParam String stationName, String lineNumber) {
        return ResponseEntity.ok(station_LineService.getBusArrivalsAtStation(stationName, lineNumber));
    }
}
