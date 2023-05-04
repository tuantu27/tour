package com.example.tour.repository;

import com.example.tour.entity.ToursEntity;
import com.example.tour.model.dto.ToursDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITourRepository extends JpaRepository<ToursEntity,Long> {
    List<ToursEntity> getToursEntitiesByAccount_AccountIdAndStatus(Long accountId,int status);



}
