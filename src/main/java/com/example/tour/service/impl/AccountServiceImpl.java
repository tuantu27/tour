package com.example.tour.service.impl;

import com.example.tour.config.app.CustomStatus;
import com.example.tour.entity.AccountsEntity;
import com.example.tour.entity.RoleEntity;
import com.example.tour.exception.CustomException;
import com.example.tour.model.AccountMapper;
import com.example.tour.model.dto.AccountsDTO;
import com.example.tour.model.dto.IRequestCreateAccount;
import com.example.tour.repository.AccountRepository;
import com.example.tour.repository.RoleRepository;
import com.example.tour.service.AccountService;
import com.example.tour.utils.Formatter;
import com.example.tour.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {
    private Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountsRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<AccountsEntity> getAllAccounts() {
        return accountsRepository.findAll();
    }

    @Override
    public Optional<AccountsEntity> getAccountById(Long accountId) throws Exception {
        if (!Formatter.isNotNull(accountId)) {
            log.error("get Account by Id error because id null");
            throw new CustomException(CustomStatus.SOMETHING_WENT_WRONG);
        }
        Map<String, Object> dataFind = new HashMap<>();
        dataFind.put("accountId", accountId);
        return accountsRepository.findById(accountId);
    }

    @Override
    public AccountsDTO createAccount(IRequestCreateAccount requestCreateAccount) throws Exception {
        if (requestCreateAccount == null) {
            log.error("Request create Account is null");
            throw new CustomException(CustomStatus.INVALID_INPUT);
        }
        Long accountId = requestCreateAccount.getAccountId();
        if (Formatter.isNotNull(accountId)) {
            boolean isExist = this.isExistAccount(accountId);
            if (isExist) {
                log.error("The _id: " + accountId + " already exist.");
                throw new CustomException(CustomStatus.DUPLICATED_ID, "The _id: " + accountId + " already exist.");
            }
        }
        AccountsEntity accountsEntity;
        try {
            accountsEntity = Utils.copy(AccountService.class, requestCreateAccount);
            assert accountsEntity != null;
            accountsEntity.setPassword(new BCryptPasswordEncoder().encode(requestCreateAccount.getPassword()));
            AccountsEntity accountRole = accountsRepository.save(accountsEntity);
            if (Formatter.isNotNull(accountRole)) {
                RoleEntity role = new RoleEntity();
                role.setUser(accountRole);
                role.setRole("Member");
                roleRepository.save(role);
            }
        } catch (Exception e) {
            log.error("Error saving insurance policy: " +
                    "\n - _id: " + accountId +
                    "\n - Error Message: " + e.getLocalizedMessage());
            throw new CustomException(CustomStatus.SOMETHING_WENT_WRONG);
        }
        return accountMapper.convertToAccountDTO(accountsEntity);
    }

    private boolean isExistAccount(Long id) throws Exception {
        try {
            Optional<AccountsEntity> accounts = this.getAccountById(id);
            if (accounts != null)
                return true;
        } catch (Exception e) {
            log.error("Error when searching for tour:" +
                    "\n- _id: " + id +
                    "\n- Error Message: " + e.getLocalizedMessage());
            throw new CustomException(CustomStatus.SOMETHING_WENT_WRONG);
        }
        return false;
    }
}