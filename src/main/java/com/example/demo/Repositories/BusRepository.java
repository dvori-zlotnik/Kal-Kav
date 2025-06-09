package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Bus;
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
public Bus save(Bus bus);
public Optional<Bus> findById(Long id);
public List<Bus> findAll();
public void deleteById(Long id);
public void delete(Bus bus);
public long count();
public boolean existsById(Long id); 
}
