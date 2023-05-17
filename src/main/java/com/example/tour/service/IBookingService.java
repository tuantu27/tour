package com.example.tour.service;

import com.example.tour.model.dto.BookingDTO;

public interface IBookingService {
    Long saveBooking(BookingDTO bookingDTO);
    BookingDTO getById(Long booking_id);
}
