package com.example.tour.service;

import com.example.tour.model.dto.AccountsDTO;

public interface IAccountService {
    AccountsDTO getById(Long id);
    AccountsDTO getByCompanyId(Long companyId);

    void updateAccount(AccountsDTO accountsDTO);
}
