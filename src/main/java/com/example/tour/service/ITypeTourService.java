package com.example.tour.service;

import com.example.tour.model.dto.ToursDTO;
import com.example.tour.model.dto.TypeTourDTO;

import java.util.List;

public interface ITypeTourService {
    List<TypeTourDTO> getAll();
}