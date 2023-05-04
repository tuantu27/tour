package com.example.tour.controller;

import com.example.tour.model.dto.AccountsDTO;
import com.example.tour.model.dto.CompanysDTO;
import com.example.tour.service.IAccountService;
import com.example.tour.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ICompanyService iCompanyService;
    @Autowired
    IAccountService iAccountService;



    @GetMapping("/company")
    public String view_company(Model model) {
        List<CompanysDTO> lstCompany = iCompanyService.getAll();
        model.addAttribute("lstCompany",lstCompany);

        return "admin";
    }
    @GetMapping(value="/change-status/{valueStt}/{id}")
    public String change_status(Model model, @PathVariable("valueStt") int status, @PathVariable("id")Long id) {
        CompanysDTO companysDTO = iCompanyService.getById(id);
        AccountsDTO accountsDTO = iAccountService.getByCompanyId(id);
        companysDTO.setStatus(status);
        accountsDTO.setStatus(status);
        iCompanyService.updateProfile(companysDTO);
        iAccountService.updateAccount(accountsDTO);

        return "redirect:/admin/company";
    }

    @GetMapping("/new-company")
    public String view_add_company(Model model) {
        CompanysDTO companysDTO = new CompanysDTO();
        model.addAttribute("companyDTO",companysDTO);
        return "newCompany";
    }

    @PostMapping(value = "/addCompany")
    public String saveCompany(Model model, @ModelAttribute("companyDTO")CompanysDTO companysDTO){
        iCompanyService.saveCompany(companysDTO);
        return "redirect:/admin/company";

    }



}
