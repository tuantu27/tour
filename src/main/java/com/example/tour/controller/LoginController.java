package com.example.tour.controller;

import com.example.tour.entity.AccountsEntity;
import com.example.tour.repository.AccountRepository;
import com.example.tour.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    AccountRepository accountRepository;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("data");
        return "login.html";
    }
    @PostMapping("/login")
    public String login(Model model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        List<AccountsEntity> users = accountRepository.findAll();
        for (AccountsEntity user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                model.addAttribute("test");
                return "redirect:/home";
            } else
                session.setAttribute("currentUser", user);
            return "login.html";
        }
        return "redirect:/login"; // views
    }
}
