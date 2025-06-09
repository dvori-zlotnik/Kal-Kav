package com.example.demo.Controllers;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTOs.TravelDTO;
import com.example.demo.Services.TravelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/travel")
public class TravelController {
    @Autowired
    public TravelService travelService;
    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody TravelDTO travelDTO) {
        if(travelService.createTravel(travelDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
         else {
            return ResponseEntity.badRequest().build();
        }
    } 
    @GetMapping("/getbyid")
    public ResponseEntity<TravelDTO> get(@RequestParam Long id) {
        TravelDTO travelDTO = travelService.getTravelById(id);
        if(travelDTO != null) {
            return ResponseEntity.ok(travelDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getall")
    public ResponseEntity<Iterable<TravelDTO>> getAll() {
        return ResponseEntity.ok(travelService.getAllTravels());
    }
    @GetMapping("schedule")
    public ResponseEntity<Iterable<TravelDTO>> schedule(Long lineId,int type,LocalTime time)
    {
        return ResponseEntity.ok(travelService.k(lineId, type, time));
    }
  
}
