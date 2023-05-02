package com.example.tour.controller;

import com.example.tour.entity.AccountsEntity;
import com.example.tour.entity.RoleEntity;
import com.example.tour.repository.AccountRepository;
import com.example.tour.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/create")
    public String create() {
        return "account/register.html";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute @Valid AccountsEntity accounts , @RequestParam("role") String roles) throws IOException {
        accounts.setPassword(new BCryptPasswordEncoder().encode(accounts.getPassword()));
        AccountsEntity user1 = accountRepository.save(accounts);
        if (user1 != null){
            RoleEntity role = new RoleEntity();
            role.setUser(user1);
            if("SubAdmin".equals(roles)){

                role.setRole("SubAdmin");
            }else if("Admin".equals(roles)){
                role.setRole("Admin");
            }

            roleRepository.save(role);
        }
        return "redirect:/home";
    }
}