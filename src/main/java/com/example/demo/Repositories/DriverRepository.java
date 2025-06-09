package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Driver;
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
public Driver save(Driver Driver);
public Optional<Driver> findById(Long id);
public List<Driver> findAll();
public void deleteById(Long id);
public void delete(Driver Driver);
public long count();
public boolean existsById(Long id);     
    

    
}
