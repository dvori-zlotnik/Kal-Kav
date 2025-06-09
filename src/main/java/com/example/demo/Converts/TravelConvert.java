package com.example.demo.Converts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DTOs.TravelDTO;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.BusRepository;
import com.example.demo.Repositories.DriverRepository;
import com.example.demo.Repositories.LineRepository;

public class TravelConvert {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private BusRepository busRepository;
    public Travel toTravel(TravelDTO travel) {
        Travel travel1 = new Travel();
        // travel1.setId(travel.getId());
        travel1.setDriver(driverRepository.findById(travel.getDriverId()).orElse(null));
        travel1.setLine(lineRepository.findById(travel.getLineId()).orElse(null));
        travel1.setBus(busRepository.findById(travel.getBusId()).orElse(null));
        travel1.setDeparture_time(null == travel.getDeparture_time() ? null : travel.getDeparture_time());
        return travel1;
    }
    public TravelDTO toTravelDTO(Travel travel) {
    //         private Long id;
    // private String LineNumber;
    // private String LineSource;
    // private String LineDestination;
    // private long lineId;
    // private long driverId;
    // private String DriverName;
    // private String DriverPhoneNumber;
    // private String busNumber;
    // private int BusCapacity;
    // private long busId;
    // private Time Departure_time;
        TravelDTO travelDTO = new TravelDTO();
        travelDTO.setId(travel.getId());
        if(travel.getLine() != null) {
        travelDTO.setLineNumber(null == travel.getLine().getNumber() ? null : travel.getLine().getNumber());
        travelDTO.setLineSource(null == travel.getLine().getSource() ? null : travel.getLine().getSource());
        travelDTO.setLineDestination(null == travel.getLine().getDestination() ? null : travel.getLine().getDestination());
        }
        if(travel.getDriver()!= null) {
        travelDTO.setDriverId(travel.getDriver().getId());
        travelDTO.setDriverName(null == travel.getDriver().getName() ? null : travel.getDriver().getName());
        travelDTO.setDriverPhoneNumber(null == travel.getDriver().getPhoneNumber() ? null : travel.getDriver().getPhoneNumber());
        }
        if(travel.getBus()!= null) {
        travelDTO.setBusNumber(null == travel.getBus().getBusNumber() ? null : travel.getBus().getBusNumber());
        travelDTO.setBusCapacity(0 == travel.getBus().getCapacity() ? 0 : travel.getBus().getCapacity());
        }
        travelDTO.setDeparture_time(null == travel.getDeparture_time() ? null : travel.getDeparture_time());
        return travelDTO;
    }
    public List<TravelDTO> toTravelDTOList(List<Travel> travels) {
        return travels.stream()
                .map(this::toTravelDTO)
                .toList();
    }
    public List<Travel> toTravelList(List<TravelDTO> travels) {
        return travels.stream()
                .map(this::toTravel)
                .toList();
    }
}

    

