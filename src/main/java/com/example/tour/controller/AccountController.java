package com.example.tour.controller;

import com.example.tour.entity.AccountsEntity;
import com.example.tour.entity.RoleEntity;
import com.example.tour.repository.AccountRepository;
import com.example.tour.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/new")
    public String create() {
        return "account/new.html";
    }
    @PostMapping("/new")
    public String create(@ModelAttribute @Valid AccountsEntity accounts) throws IOException {
        accounts.setPassword(new BCryptPasswordEncoder().encode(accounts.getPassword()));
        AccountsEntity user1 = accountRepository.save(accounts);
        if (user1 != null){
            RoleEntity role = new RoleEntity();
            role.setUser(user1);
            role.setRole("Admin");
            roleRepository.save(role);
        }
        return "redirect:/product/search";
    }
}