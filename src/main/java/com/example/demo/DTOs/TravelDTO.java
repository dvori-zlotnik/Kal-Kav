package com.example.demo.DTOs;
import java.sql.Time;
import java.time.LocalTime;

import lombok.Data;
@Data
public class TravelDTO {
    private Long id;
    private String LineNumber;
    private String LineSource;
    private String LineDestination;
    private long lineId;
    private long driverId;
    private String DriverName;
    private String DriverPhoneNumber;
    private String busNumber;
    private int BusCapacity;
    private long busId;
    private LocalTime Departure_time;
}
