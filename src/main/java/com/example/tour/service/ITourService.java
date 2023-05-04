package com.example.tour.service;

import com.example.tour.model.dto.ToursDTO;

import java.util.List;

public interface ITourService {
    List<ToursDTO> getAll();
    Long saveTour(ToursDTO toursDTO);
    void updateTour(ToursDTO toursDTO);
    void deleteTour(Long id);
    ToursDTO getById(Long tourId);
    List<ToursDTO> getToursByAccountId(Long accountId);


}
