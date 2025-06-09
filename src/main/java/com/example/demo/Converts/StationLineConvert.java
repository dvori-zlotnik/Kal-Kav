package com.example.demo.Converts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.DTOs.Station_LineDto;

import com.example.demo.Models.Line;
import com.example.demo.Models.Station;
import com.example.demo.Models.Station_Line;
import com.example.demo.Repositories.LineRepository;
import com.example.demo.Repositories.StationRepository;

public class StationLineConvert {
    @Autowired
    public LineRepository lineRepository;
    @Autowired
    public StationRepository stationRepository;
    
    public Station_LineDto toDto(Station_Line stationLine) {
        Station_LineDto stationLineDto = new Station_LineDto();
        stationLineDto.setId(stationLine.getId());
        if(stationLine.getLine()!=null)
        {
        Long lineId = stationLine.getLine().getId();
        stationLineDto.setLineId(lineId);
        }
        if(stationLine.getStation()!=null)
        stationLineDto.setStationId(stationLine.getStation().getId());
        stationLineDto.setStationOrder(stationLine.getStationOrder());
        return stationLineDto;
    }
public Station_Line toEntity(Station_LineDto stationLineDto) {
    Station_Line stationLine = new Station_Line();
    // stationLine.setId(stationLineDto.getId());
    Line line = lineRepository.findById(stationLineDto.getLineId()).orElse(null);
    stationLine.setLine(line);
    Station station = stationRepository.findById(stationLineDto.getStationId()).orElse(null);
    stationLine.setStation(station);
    stationLine.setStationOrder(stationLineDto.getStationOrder());

    return stationLine;
}

    public List<Station_Line> toListEntity(List<Station_LineDto> stationLineDtos) {
        return stationLineDtos.stream()
                .map(this::toEntity)
                .toList();
    }
    public List<Station_LineDto> toListDto(List<Station_Line> stationLines) {
        return stationLines.stream()
                .map(this::toDto)
                .toList();
    }

}
