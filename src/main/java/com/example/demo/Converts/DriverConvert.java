package com.example.demo.Converts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DTOs.DriverDTO;
import com.example.demo.Models.Driver;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.TravelRepository;

public class DriverConvert {
    @Autowired
        public TravelRepository travelRepository;

    // Convert DriverDTO to Driver entity
    public Driver convertToEntity(com.example.demo.DTOs.DriverDTO driverDTO) {
        Driver driver = new Driver();
        // driver.setId(driverDTO.getId());
        driver.setName(driverDTO.getName());
        driver.setPhoneNumber(driverDTO.getPhoneNumber());
        if(driverDTO.getTravelIds()!=null)
            driver.setTravels(travelRepository.findAllById(driverDTO.getTravelIds()));
        else
            driver.setTravels(new ArrayList<>());
        return driver;
    }

    // Convert Driver entity to DriverDTO
    public DriverDTO convertToDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setPhoneNumber(driver.getPhoneNumber());
        driver.setPhoneNumber(driverDTO.getPhoneNumber());
        driverDTO.setTravelIds(driver.getTravels().stream()
                         .map(Travel::getId) // הנחה שיש שיטה getId ב-Travel
                         .collect(Collectors.toList()));
        return driverDTO;
    }

    public List<DriverDTO> convertToDTOList(List<Driver> drivers) {
        return drivers.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<Driver> convertToEntityList(List<DriverDTO> driverDTOs) {
        return driverDTOs.stream()
                .map(this::convertToEntity)
                .toList();
    }

}
