package com.example.tour.service;

import com.example.tour.entity.TypeTourEntity;
import com.example.tour.model.dto.ToursDTO;
import com.example.tour.model.dto.TypeTourDTO;
import com.example.tour.repository.ITypeTourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeTourService implements ITypeTourService {

    @Autowired
    ITypeTourRepository iTypeTourRepository;
    @Override
    public List<TypeTourDTO> getAll() {
        List<TypeTourEntity> typeTourEntities = iTypeTourRepository.findAll();
        List<TypeTourDTO> typeTourDTOS = new ArrayList<>();
        for (TypeTourEntity tour : typeTourEntities) {
            TypeTourDTO typeTourDTO = tour.toDto(tour);
            typeTourDTOS.add(typeTourDTO);

        }
        return typeTourDTOS;
    }

    @Override
    public void saveTypeTour(TypeTourDTO typeTourDTO) {
        TypeTourEntity typeTour = new TypeTourEntity();
        typeTour.setNameTypeTour(typeTourDTO.getNameTypeTour());
        typeTour.setRegion(typeTourDTO.getRegion());
        iTypeTourRepository.save(typeTour);
    }
}
