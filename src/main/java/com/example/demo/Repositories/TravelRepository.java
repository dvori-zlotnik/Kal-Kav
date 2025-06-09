package com.example.demo.Repositories;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Line;
import com.example.demo.Models.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query("SELECT t FROM Travel t WHERE t.line.id = :lineId AND t.Departure_time BETWEEN :startTime AND :endTime")
    List<Travel> findTravelsByLineAndDepartureTimeBetween(
            @Param("lineId") Long lineId,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);
@EntityGraph(attributePaths = {"line", "driver", "bus"})
public Optional<Travel> findById(Long id);
@EntityGraph(attributePaths = {"line", "driver", "bus"})
public List<Travel> findByLine(Line line);   
   
}
