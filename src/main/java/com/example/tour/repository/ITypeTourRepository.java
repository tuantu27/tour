package com.example.tour.repository;

import com.example.tour.entity.TypeTourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeTourRepository  extends JpaRepository<TypeTourEntity,Long> {
}
