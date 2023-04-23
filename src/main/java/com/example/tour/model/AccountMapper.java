package com.example.tour.model;

import com.example.tour.entity.AccountsEntity;
import com.example.tour.model.dto.AccountsDTO;
import org.springframework.stereotype.Component;

@Component

public class AccountMapper {
    public AccountsDTO convertToAccountDTO(AccountsEntity accountsEntity) {
        AccountsDTO accountsDTO = new AccountsDTO();
        accountsDTO.setAccountId(accountsEntity.getAccountId());
        return accountsDTO;
    }
}
