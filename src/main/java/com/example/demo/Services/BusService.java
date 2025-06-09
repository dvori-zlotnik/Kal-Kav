package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Converts.BusConverter;
import com.example.demo.DTOs.BusDTO;
import com.example.demo.Repositories.BusRepository;

@Service
public class BusService {
    @Autowired
    public BusRepository busRepository;
    @Autowired
    public BusConverter busConverter;

    public List<BusDTO> getAll() {
        return busConverter.toDTOs(busRepository.findAll());
    }

    public boolean add(BusDTO busDTO) {
        if(busRepository.existsById(busDTO.getId())) {
            return false;
        } else {
            busRepository.save(busConverter.toEntity(busDTO));
            return true;
        }
    }

    public BusDTO getById(Long id) {
        if(busRepository.existsById(id)) {
            return busConverter.toDTO(busRepository.findById(id).get());
        } else {
            return null;
        }
    }
    
}
