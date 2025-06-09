package com.example.demo.Converts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.DTOs.BusDTO;
import com.example.demo.Models.Bus;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.BusRepository;
import com.example.demo.Repositories.TravelRepository;

public class BusConverter {
    @Autowired
    public BusRepository busRepository;
    @Autowired
    public TravelRepository travelRepository;
    public BusDTO toDTO(Bus bus) {
        BusDTO busDTO = new BusDTO();
        busDTO.setId(bus.getId());
        busDTO.setBusNumber(null == bus.getBusNumber() ? null : bus.getBusNumber().toString());
        busDTO.setCapacity(0 == bus.getCapacity() ? 0 : bus.getCapacity());
        busDTO.setTravelIds(bus.getTravels().stream()
                                 .map(Travel::getId) // הנחה שיש שיטה getId ב-Travel
                                 .collect(Collectors.toList()));
        return busDTO;
    }
    public Bus toEntity(BusDTO busDTO) {
        Bus bus = new Bus();
        // bus.setId(busDTO.getId());
        bus.setBusNumber(null == busDTO.getBusNumber() ? null : busDTO.getBusNumber());
        bus.setCapacity(0 == busDTO.getCapacity() ? 0 : busDTO.getCapacity());
        if(bus.getTravels()!=null)
            bus.setTravels(travelRepository.findAllById(busDTO.getTravelIds()));
        else
            bus.setTravels(new ArrayList<>());
        return bus;
    }
    public List<BusDTO> toDTOs(List<Bus> buses) {
        return buses.stream().map(this::toDTO).toList();
    }
    public List<Bus> toEntities(List<BusDTO> busDTOs) {
        return busDTOs.stream().map(this::toEntity).toList();
    }
}
