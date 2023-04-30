package com.example.tour.repository;

import com.example.tour.entity.MultipleTypeTourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMultipleTypeTourRepository extends JpaRepository<MultipleTypeTourEntity,Long> {
}
