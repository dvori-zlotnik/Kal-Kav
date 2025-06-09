package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Converts.StationConvert;
import com.example.demo.DTOs.StationDTO;
import com.example.demo.Repositories.StationRepository;

@Service
public class StationService {
     @Autowired
    public StationRepository stationRepository;
    @Autowired
    public StationConvert stationConverter;

    public List<StationDTO> getAll() {
        return stationConverter.toStationDTOList(stationRepository.findAll());
    }

    public boolean add(StationDTO StationDTO) {
        if(stationRepository.existsById(StationDTO.getId())) {
            return false;
        } else {
            stationRepository.save(stationConverter.toStation(StationDTO));
            return true;
        }
    }

    public StationDTO getById(Long id) {
        if(stationRepository.existsById(id)) {
            return stationConverter.toStationDTO(stationRepository.findById(id).get());
        } else {
            return null;
        }
    }
}
