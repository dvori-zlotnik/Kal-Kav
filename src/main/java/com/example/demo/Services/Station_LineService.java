package com.example.demo.Services;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Converts.LineConvert;
import com.example.demo.Converts.StationLineConvert;
import com.example.demo.DTOs.Station_LineDto;
import com.example.demo.Models.Line;
import com.example.demo.Models.Station;
import com.example.demo.Models.Station_Line;
import com.example.demo.Models.Travel;
import com.example.demo.Repositories.LineRepository;
import com.example.demo.Repositories.StationRepository;
import com.example.demo.Repositories.Station_LineRepository;
import com.example.demo.Repositories.TravelRepository;

@Service
public class Station_LineService {
    @Autowired
    public Station_LineRepository station_LineRepository;
    @Autowired
    public StationLineConvert station_LineConvert;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private LineConvert lineConvert;

    public List<Station_LineDto> getAll() {
        return station_LineConvert.toListDto(station_LineRepository.findAll());
    }

    public Station_LineDto add(Station_LineDto Station_LineDTO) {
        if (station_LineConvert.toEntity(Station_LineDTO).getId() != null
                && station_LineRepository.existsById(station_LineConvert.toEntity(Station_LineDTO).getId())) {
            return null;
        } else {
            int order = Station_LineDTO.getStationOrder();
            Long lineId = Station_LineDTO.getLineId();
            List<Station_Line> stationLines = station_LineRepository.findByLine_Id(lineId);
            if (stationLines.size() < order + 1)
                Station_LineDTO.setStationOrder(stationLines.size() + 1);
            else {
                List<Station_Line> filteredStationLines = stationLines.stream()
                        .filter(stationLine -> stationLine.getStationOrder() >= order)
                        .collect(Collectors.toList());
                for (Station_Line stationLine : filteredStationLines)
                    stationLine.setStationOrder(stationLine.getStationOrder() + 1);
            }
            Station_Line s = station_LineConvert.toEntity(Station_LineDTO);
            Station_LineDto ss = station_LineConvert.toDto(station_LineRepository.save(s));
            return ss;
        }
    }

    public List<Station_LineDto> AllStationInLine(Long lineId) {
        return station_LineConvert.toListDto(station_LineRepository.findByLine_Id(lineId));
    }

    public List<Station_LineDto> locationOfTheBusesAlongTheEntireRoute(Long lineId) {
        List<Station_Line> stationLines = station_LineRepository.findByLine_Id(lineId);
        int count = stationLines.size();
        LocalTime currentTime = LocalTime.now();
        List<Travel> travels = travelRepository.findTravelsByLineAndDepartureTimeBetween(lineId,
                currentTime.minus(Duration.ofMinutes(count)), currentTime);
        List<Station_LineDto> result = new ArrayList<>();
        for (Travel travel : travels) {
            Duration duration = Duration.between(travel.getDeparture_time(), currentTime);
            long minutesDifference = duration.toMinutes();
            Station_Line stationLine = stationLines.stream()
                    .filter(sl -> sl.getStationOrder() == minutesDifference)
                    .findFirst()
                    .orElse(null);

            if (stationLine != null) {
                result.add(station_LineConvert.toDto(stationLine));
            }
            // else {
            // // טיפול במקרה שבו stationLine הוא null, לדוגמה:
            // // result.add(null); // או להוסיף הודעת שגיאה או טיפול אחר
            // }

        }
        return result;
    }

    public boolean deleteById(Long id) {
        if (station_LineRepository.existsById(id)) {
            Station_Line station_Line = station_LineRepository.findById(id).get();
            Long lineId = station_Line.getLine().getId();
            List<Station_Line> stationLines = station_LineRepository.findByLine_Id(lineId);
            int order = station_Line.getStationOrder();
            for (Station_Line stationLine : stationLines) {
                if (stationLine.getStationOrder() > order)
                    stationLine.setStationOrder(stationLine.getStationOrder() - 1);
            }
            station_LineRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Station_LineDto getById(Long id) {
        if (station_LineRepository.existsById(id)) {
            return station_LineConvert.toDto(station_LineRepository.findById(id).get());
        } else {
            return null;
        }
    }

    public List<Map<String, Object>> getBusArrivalsAtStation(String stationName, String lineNumber) {
        Station station = stationRepository.findByName(stationName)
                .orElseThrow(() -> new RuntimeException("Station not found"));

        List<Station_Line> stationLines;

        if (lineNumber.equals("*")) {
            // קבל את כל הקווים שעוברים בתחנה
            stationLines = station_LineRepository.findByStation(station);
        } else {
            Line line = lineRepository.findByNumber(lineNumber)
                    .orElseThrow(() -> new RuntimeException("Line not found"));

            stationLines = station_LineRepository.findAllByLineIdAndStationId(station.getId(), line.getId());
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Station_Line stationLine : stationLines) {
            int stationOrder = stationLine.getStationOrder();
            Line line = stationLine.getLine();
            List<Travel> travels = travelRepository.findByLine(line);

            for (Travel travel : travels) {
                LocalTime arrivalTime = travel.getDeparture_time().plusMinutes(stationOrder);

                Map<String, Object> map = new HashMap<>();
                map.put("line", lineConvert.toLineDTO(line)); // שימוש ב־LineDTO במקום Line
                map.put("arrivalTime", arrivalTime);

                result.add(map);
            }
        }
        result.sort(Comparator.comparing(m -> (LocalTime) m.get("arrivalTime")));
        return result;
    }

}
