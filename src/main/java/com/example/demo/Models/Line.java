package com.example.demo.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Line {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String source;
    private String destination;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "line",fetch = FetchType.LAZY)
    private List<Travel> travels;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "line",fetch = FetchType.LAZY)
    private List<Station_Line> stationLines;

}