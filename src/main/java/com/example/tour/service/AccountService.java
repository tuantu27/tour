package com.example.tour.service;


import com.example.tour.entity.AccountsEntity;
import com.example.tour.model.dto.AccountsDTO;
import com.example.tour.model.dto.IRequestCreateAccount;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountsDTO createAccount(IRequestCreateAccount requestCreateAccount) throws Exception;
    List<AccountsEntity> getAllAccounts();
    Optional<AccountsEntity> getAccountById(Long accountId) throws Exception;
}
