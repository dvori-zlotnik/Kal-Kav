package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.StationDTO;
import com.example.demo.Services.StationService;
@RestController
@RequestMapping("/api/station")
public class StationController {
     @Autowired
    public StationService stationService;

    @GetMapping("/getall")
    public ResponseEntity<Iterable<StationDTO>> getAll() {
        return ResponseEntity.ok(stationService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(StationDTO StationDTO) {
        if (stationService.add(StationDTO))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getbyid")
    public ResponseEntity<StationDTO> getById(Long id) {
        if (stationService.getById(id) != null)
            return ResponseEntity.ok(stationService.getById(id));
        return ResponseEntity.notFound().build();
    }

}
