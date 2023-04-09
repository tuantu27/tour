package com.example.tour.repository;

import com.example.tour.entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountsEntity,Integer> {
    AccountsEntity findByUsername(String username);
}
