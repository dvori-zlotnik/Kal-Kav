package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Bus;
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

}
