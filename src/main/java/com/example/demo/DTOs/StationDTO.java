package com.example.demo.DTOs;
import java.util.List;
import lombok.Data;
@Data
public class StationDTO {
    private Long id;
    private String name;
    private List<Long> stationLineIds;
}
