package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTOs.BusDTO;
import com.example.demo.Services.BusService;

@RestController
@RequestMapping("/api/bus")
public class BusController {
    @Autowired
    public BusService busService;
    @GetMapping("/getall")
    public ResponseEntity<Iterable<BusDTO>> getall() {
        return ResponseEntity.ok(busService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(BusDTO busDTO) {
        if (busService.add(busDTO))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getbyid")
    public ResponseEntity<BusDTO> getById(Long id) {
        if (busService.getById(id) != null)
            return ResponseEntity.ok(busService.getById(id));
        return ResponseEntity.notFound().build();
    }

}
