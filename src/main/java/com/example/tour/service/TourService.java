package com.example.tour.service;

import com.example.tour.entity.ToursEntity;
import com.example.tour.model.dto.ToursDTO;
import com.example.tour.repository.AccountRepository;
import com.example.tour.repository.ITourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourService implements ITourService {
    @Autowired
    ITourRepository iTourRepository;

    @Autowired
    AccountRepository accountRepository;


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

    @Override
    public Long saveTour(ToursDTO toursDTO) {
        ToursEntity toursEntity = new ToursEntity();
        toursEntity.setTourName(toursDTO.getTourName());
        toursEntity.setDuration(toursDTO.getDuration());
        toursEntity.setStartDate(toursDTO.getStartDate());
        toursEntity.setImgName(toursDTO.getImgName());
        toursEntity.setStatus(1);
        toursEntity.setDescription(toursDTO.getDescription());
        toursEntity.setPriceAdult(toursDTO.getPriceAdult());
        toursEntity.setPriceChildren(toursDTO.getPriceChildren());
        toursEntity.setPriceInfant(toursDTO.getPriceInfant());
        toursEntity.setAccount(accountRepository.findById(toursDTO.getAccountId()).get());
        ToursEntity entity = iTourRepository.save(toursEntity);
        return entity.getTourId();
    }

    @Override
    public ToursDTO getById(Long tourId) {
        Optional<ToursEntity> toursEntity = iTourRepository.findById(tourId);
        ToursDTO toursDTO = toursEntity.get().toDto(toursEntity.get());
        return toursDTO;
    }
}
