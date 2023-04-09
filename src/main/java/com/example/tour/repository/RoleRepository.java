package com.example.tour.repository;

import com.example.tour.entity.AccountsEntity;
import com.example.tour.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
