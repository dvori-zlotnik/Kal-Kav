package com.example.demo.Services;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Converts.TravelConvert;
import com.example.demo.DTOs.TravelDTO;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.TravelRepository;

@Service
public class TravelService {
    @Autowired
    public TravelRepository travelRepository;
    @Autowired
    public TravelConvert travelConvert;

    public boolean createTravel(TravelDTO travelDTO) {
        Travel travel = travelConvert.toTravel(travelDTO);
        if (travel.getId() == null) {
            travelRepository.save(travel);
            return true;
        }
        if (!travelRepository.existsById(travel.getId())) {
            travelRepository.save(travel);
            return true;
        } else {
            return false;
        }
    }

    public TravelDTO getTravelById(Long id) {
        Travel travel = travelRepository.findById(id).orElse(null);
        if (travel != null) {
            return travelConvert.toTravelDTO(travel);
        } else {
            return null;
        }
    }

    public boolean updateTravel(TravelDTO travelDTO) {
        Travel travel = travelConvert.toTravel(travelDTO);
        if (travelRepository.existsById(travel.getId())) {
            travelRepository.save(travel);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteTravel(Long id) {
        if (travelRepository.existsById(id)) {
            travelRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<TravelDTO> getAllTravels() {
        return travelConvert.toTravelDTOList(travelRepository.findAll());
    }

    public List<TravelDTO> k(Long lineId,int typeSearch, LocalTime time) {
        // switch (typeSearch) {
        //     case 1:
                
        //         break;
        
        //     default:
        //         break;
        // }
        List<Travel> travels = travelRepository.findTravelsByLineAndDepartureTimeBetween(lineId,
                time.minus(Duration.ofMinutes(30)), time.plus(Duration.ofMinutes(30)));
        return travelConvert.toTravelDTOList(travels);
    }
}
