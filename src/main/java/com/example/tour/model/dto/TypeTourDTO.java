package com.example.tour.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeTourDTO {
    private  Long typeTourId;
    private String nameTypeTour;
    private String region; // 1 Mien Bac , 2 Mien Trung ,3 Mien Nam , 4 Tay Nguyen
    private List<MultipleTypeTourDTO> multipleTypeTourDTOS;

}