package com.example.demo.DTOs;

import java.util.List;

import lombok.Data;


@Data
public class DriverDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private List<Long> travelIds;
}
