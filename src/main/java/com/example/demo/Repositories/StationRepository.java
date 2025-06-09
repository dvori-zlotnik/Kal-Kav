package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> { 
public Station save(Station Station);
public Optional<Station> findById(Long id);
public Optional<Station> findByName(String stationName);
public List<Station> findAll();
public void deleteById(Long id);
public void delete(Station Station);
public long count();
public boolean existsById(Long id); 
}
