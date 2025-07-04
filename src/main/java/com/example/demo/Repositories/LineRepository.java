package com.example.demo.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Line;
@Repository
public interface LineRepository extends JpaRepository<Line, Long> {
public Optional<Line> findByNumber(String number); 
}
