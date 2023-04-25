package com.example.tour.service;

import com.example.tour.entity.ToursEntity;
import com.example.tour.model.dto.ToursDTO;
import com.example.tour.repository.ITourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourService implements ITourService {
    @Autowired
    ITourRepository iTourRepository;


    @Override
    public List<ToursDTO> getAll() {
        List<ToursEntity> toursEntityList = iTourRepository.findAll();
        List<ToursDTO> toursDTOS = new ArrayList<>();
        for(ToursEntity toursEntity : toursEntityList){
            ToursDTO toursDTO = toursEntity.toDto(toursEntity);
            toursDTOS.add(toursDTO);
        }
        return toursDTOS;
    }
}
