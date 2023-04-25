package com.example.tour.controller;

import com.example.tour.model.dto.ToursDTO;
import com.example.tour.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ITourService iTourService;
    @GetMapping(value = "/home")
    public String showHomePage(Model model){
    //    List<ToursDTO> lstTour =iTourService.getAll();
     //   model.addAttribute("lstTour", lstTour);
        return "home";
    }
}
