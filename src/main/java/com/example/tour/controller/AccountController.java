package com.example.tour.controller;

import com.example.tour.entity.AccountsEntity;
import com.example.tour.model.dto.AccountsDTO;
import com.example.tour.model.dto.IRequestCreateAccount;
import com.example.tour.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/create")
    public String create() {
        return "account/new.html";
    }

    @PostMapping("/create")
    public String create(@Valid @RequestBody IRequestCreateAccount createAccount, Model model){
        try {
            accountService.createAccount(createAccount);
            return "redirect:/account/search";
        }catch (Exception e){
            return null;
        }
    }
    @GetMapping("/search")
    public String search() {
        return "account/search.html";
    }

}
