package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Converts.LineConvert;
import com.example.demo.DTOs.LineDTO;
import com.example.demo.Repositories.LineRepository;

@Service
public class LineService {
     @Autowired
    public LineRepository lineRepository;
    @Autowired
    public LineConvert lineConverter;

    public List<LineDTO> getAll() {
        return lineConverter.toLineDTOList(lineRepository.findAll());
    }

    public boolean add(LineDTO LineDTO) {
        if(lineRepository.existsById(LineDTO.getId())) {
            return false;
        } else {
            lineRepository.save(lineConverter.toLine(LineDTO));
            return true;
        }
    }
    public LineDTO getById(Long id) {
        if(lineRepository.existsById(id)) {
            return lineConverter.toLineDTO(lineRepository.findById(id).get());
        } else {
            return null;
        }
    }
}
