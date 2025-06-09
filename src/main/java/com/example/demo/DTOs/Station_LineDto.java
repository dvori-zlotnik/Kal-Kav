package com.example.demo.DTOs;

import lombok.Data;

@Data
public class Station_LineDto {
    private Long id;
    private Long stationId;
    private Long lineId;
    private int stationOrder;
}
