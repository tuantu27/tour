package com.example.tour.service;

import com.example.tour.entity.BookingEntity;
import com.example.tour.entity.CustomersEntity;
import com.example.tour.entity.ToursEntity;
import com.example.tour.model.dto.BookingDTO;
import com.example.tour.model.dto.ToursDTO;
import com.example.tour.repository.IBookingRepository;
import com.example.tour.repository.ICustomerRepository;
import com.example.tour.repository.ITourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService implements IBookingService{
    @Autowired
    private IBookingRepository iBookingRepository;

    @Autowired
    ICustomerRepository iCustomerRepository;

    @Autowired
    ITourRepository iTourRepository;

    @Override
    public Long saveBooking(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookingTime(bookingDTO.getBookingTime());
        bookingEntity.setStatus(1);
        bookingEntity.setNumberAdult(bookingDTO.getNumberAdult());
        bookingEntity.setNumberChildren(bookingDTO.getNumberChildren());
        bookingEntity.setNumberInfant(bookingDTO.getNumberInfant());
        bookingEntity.setTotalPrice(bookingDTO.getTotalPrice());
        bookingEntity.setPaymentMethod(bookingDTO.getPaymentMethod());
        bookingEntity.setTour(iTourRepository.findById(bookingDTO.getToursDTO().getTourId()).get());
        bookingEntity.setCustomer(iCustomerRepository.findById(bookingDTO.getCustomersDTO().getCustomerId()).get());
        BookingEntity bookingEntity1 = iBookingRepository.save(bookingEntity);
        return bookingEntity1.getBookingId();
    }

    @Override
    public BookingDTO getById(Long booking_id) {
        Optional<BookingEntity> bookingEntity = iBookingRepository.findById(booking_id);
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(bookingEntity.get().getBookingId());
        bookingDTO.setBookingTime(bookingEntity.get().getBookingTime());
        bookingDTO.setPaymentMethod(bookingEntity.get().getPaymentMethod());
        bookingDTO.setNumberAdult(bookingEntity.get().getNumberAdult());
        bookingDTO.setNumberChildren(bookingEntity.get().getNumberChildren());
        bookingDTO.setNumberInfant(bookingEntity.get().getNumberInfant());
        bookingDTO.setStatus(bookingEntity.get().getStatus());
        bookingDTO.setTotalPrice(bookingEntity.get().getTotalPrice());
        Optional<ToursEntity> toursEntity = iTourRepository.findById(bookingEntity.get().getTour().getTourId());
        ToursDTO toursDTO = toursEntity.get().toDto(toursEntity.get());
        bookingDTO.setToursDTO(toursDTO);
        return bookingDTO;
    }
}
