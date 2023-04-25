package com.example.tour.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {
    private Long customerId;
    private String fullName;
    private String phoneNumber;
    private int gender;
    private Timestamp dob; //date of birth
    private String address;
    private List<ReviewsDTO> reviewsDTOS;
    private List<BookingDTO> bookingDTOS;
}
