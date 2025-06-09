package com.example.demo.DTOs;
import java.util.List;
import lombok.Data;
@Data
public class LineDTO {
    private Long id;
    private String Number;
    private String Source;
    private String Destination;
    private List<Long> travelIds;
    private List<Long> stationLineIds;
}