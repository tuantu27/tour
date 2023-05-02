package com.example.tour.service;

import com.example.tour.entity.AccountsEntity;
import com.example.tour.model.dto.AccountsDTO;
import com.example.tour.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public AccountsDTO getById(Long id) {
        AccountsEntity accountsEntity = accountRepository.findById(id).get();
        AccountsDTO accountsDTO = new AccountsDTO();
        accountsDTO.setAccountId(accountsEntity.getAccountId());
        accountsDTO.setUserName(accountsEntity.getUsername());
        return accountsDTO;
    }
}
