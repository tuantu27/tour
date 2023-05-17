package com.example.tour.repository;

import com.example.tour.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingRepository extends JpaRepository<BookingEntity,Long> {
}
