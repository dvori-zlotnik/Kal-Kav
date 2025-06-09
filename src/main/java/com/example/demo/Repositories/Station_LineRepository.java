package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Station;
import com.example.demo.Models.Station_Line;

@Repository

public interface Station_LineRepository extends JpaRepository<Station_Line, Long> {
public Station_Line save(Station_Line Station_Line);
public Optional<Station_Line> findById(Long id);
public List<Station_Line> findByStation(Station station);
public List<Station_Line> findAll();
public List<Station_Line> findAllByLineIdAndStationId(Long lineId, Long stationId);
public void deleteById(Long id);
public void delete(Station_Line Station_Line);
public long count();
public boolean existsById(Long id); 
public List<Station_Line> findByLine_Id(Long lineId);
}
