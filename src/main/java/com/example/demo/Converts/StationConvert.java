package com.example.demo.Converts;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.DTOs.StationDTO;
import com.example.demo.Models.Station;
import com.example.demo.Models.Station_Line;
import com.example.demo.Repositories.Station_LineRepository;

public class StationConvert {
    @Autowired
    public Station_LineRepository stationLineRepository;

    public StationDTO toStationDTO(Station station) {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setId(station.getId());
        stationDTO.setName(station.getName());
        List<Long> lineIds = station.getStationLines().stream()
                .map(Station_Line::getId)
                .collect(Collectors.toList());
        stationDTO.setStationLineIds(lineIds);
        return stationDTO;
    }

    public Station toStation(StationDTO stationDTO) {
        Station station = new Station();
        station.setId(stationDTO.getId());
        station.setName(stationDTO.getName());
        List<Long> stationLineIds = stationDTO.getStationLineIds();
        if (stationLineIds != null) {
            station.setStationLines(stationLineRepository.findAllById(stationLineIds));
        } 
        else {
            station.setStationLines(new ArrayList<>());
        }
        return station;
    }

    public List<StationDTO> toStationDTOList(List<Station> stations) {
        return stations.stream()
                .map(this::toStationDTO)
                .toList();
    }

    public List<Station> toStationList(List<StationDTO> stationDTOs) {
        return stationDTOs.stream()
                .map(this::toStation)
                .toList();
    }

}
