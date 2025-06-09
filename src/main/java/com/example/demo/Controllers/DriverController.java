package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.DriverDTO;
import com.example.demo.Services.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/driver")
public class DriverController {
    @Autowired
    public DriverService driverService;

    @GetMapping("getall")
    public ResponseEntity<Iterable<DriverDTO>> getAll() {
        return ResponseEntity.ok(driverService.getAll());
    }
    
    @GetMapping("/getbyid")
    public ResponseEntity<DriverDTO> getById(@RequestParam Long id) {
        DriverDTO driverDTO = driverService.getById(id);
        if(driverDTO != null) {
            return ResponseEntity.ok(driverDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("add")
    public ResponseEntity<Boolean> addDriver(@RequestBody DriverDTO driverDTO) {
        boolean savedDriver = driverService.add(driverDTO);
        return ResponseEntity.ok(savedDriver);
    }
    
    
    
}
