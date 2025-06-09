package com.example.demo.Converts;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DTOs.LineDTO;
import com.example.demo.Models.Line;
import com.example.demo.Repositories.Station_LineRepository;
import com.example.demo.Repositories.TravelRepository;


public class LineConvert {
    @Autowired
    public TravelRepository travelRepository;
    @Autowired
    public Station_LineRepository station_LineRepository;

    public Line toLine(LineDTO lineDTO) {
        Line line = new Line();
        // line.setId(lineDTO.getId());
        line.setDestination(lineDTO.getDestination());
        line.setNumber(lineDTO.getNumber());
        line.setSource(lineDTO.getSource());
        List<Long> travelIds = lineDTO.getTravelIds();
        if(travelIds!=null)
            line.setTravels(travelRepository.findAllById(lineDTO.getTravelIds())); 
        else
            line.setTravels(new ArrayList<>());
        if(lineDTO.getStationLineIds()!=null)
            line.setStationLines(station_LineRepository.findAllById(lineDTO.getStationLineIds()));
        else
            line.setStationLines(new ArrayList<>()); 
        return line;
    }
    public LineDTO toLineDTO(Line line) {
        LineDTO lineDTO = new LineDTO();
        lineDTO.setId(line.getId());
        lineDTO.setDestination(line.getDestination());
        lineDTO.setNumber(line.getNumber());
        lineDTO.setSource(line.getSource());
        lineDTO.setTravelIds(line.getTravels().stream().map(t -> t.getId()).toList());
        lineDTO.setStationLineIds(line.getStationLines().stream().map(sl -> sl.getId()).toList());  // line.getStationLines().stream().map(Station_Line::getId).collect(Collectors.toList());  // line.getStationLines().stream().map(sl -> sl.getId()).collect(Collectors.toList());  // line.getStationLines().stream().map(Station_Line::getId).collect(Collectors.toList());  //
        return lineDTO;
    }
    public List<LineDTO> toLineDTOList(List<Line> lines) {
        return lines.stream()
                .map(this::toLineDTO)
                .toList();
    }
    public List<Line> toLineList(List<LineDTO> lineDTOs) {
        return lineDTOs.stream()
                .map(this::toLine)
                .toList();  }
    
}
