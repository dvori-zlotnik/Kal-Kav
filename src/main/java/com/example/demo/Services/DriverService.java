package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Converts.DriverConvert;
import com.example.demo.DTOs.DriverDTO;
import com.example.demo.Repositories.DriverRepository;

@Service

public class DriverService {
     @Autowired
    public DriverRepository driverRepository;
    @Autowired
    public DriverConvert driverConvert;

    public List<DriverDTO> getAll() {
        return driverConvert.convertToDTOList(driverRepository.findAll());
    }

    public boolean add(DriverDTO DriverDTO) {
        if(driverRepository.existsById(DriverDTO.getId())) {
            return false;
        } else {
            driverRepository.save(driverConvert.convertToEntity(DriverDTO));
            return true;
        }
    }

    public DriverDTO getById(Long id) {
        if(driverRepository.existsById(id)) {
            return driverConvert.convertToDTO(driverRepository.findById(id).get());
        } else {
            return null;
        }
    }
}
