package com.example.demo.DTOs;

import java.util.List;


import lombok.Data;
@Data
public class BusDTO {
    private Long id;
    private String busNumber;
    private int capacity;
    private List<Long> travelIds=null;
}

