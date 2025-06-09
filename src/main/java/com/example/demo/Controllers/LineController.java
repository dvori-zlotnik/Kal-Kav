package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.LineDTO;
import com.example.demo.Services.LineService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/line")
public class LineController {
    @Autowired
    public LineService lineService;
    
    @PostMapping("add")
    public ResponseEntity<Void> add(@RequestBody LineDTO lineDTO) {
        if(lineService.add(lineDTO)) {
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(500).build();
    }
    @GetMapping("getall")
    public ResponseEntity<Iterable<LineDTO>> getAll() {
        return ResponseEntity.ok(lineService.getAll());
    }

    @GetMapping("getbyid")
    public ResponseEntity<LineDTO> getById(@RequestParam Long id) {
        LineDTO lineDTO = lineService.getById(id);
        if(lineDTO!=null) {
            return ResponseEntity.ok(lineDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }  
}
