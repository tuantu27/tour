package com.example.tour.controller;

import com.example.tour.model.dto.ToursDTO;
import com.example.tour.model.dto.TypeTourDTO;
import com.example.tour.service.ITypeTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/subAdmin")
public class SubAdminController {
    @Autowired
    ITypeTourService iTypeTourService;
    @GetMapping("/home")
    public String viewHome() {
        return "subadmin";
    }
    @GetMapping("/tour")
    public String viewTour() {
        return "tour_subAdmin";
    }

    @GetMapping("/new-Tour")
    public String viewNewTour(Model model) {
        ToursDTO toursDTO = new ToursDTO();
        TypeTourDTO typeTourDTO = new TypeTourDTO();
        List<TypeTourDTO> lstTypeTour = iTypeTourService.getAll();
        model.addAttribute("tourDTO",toursDTO);
        model.addAttribute("lstTypeTour", lstTypeTour);
        model.addAttribute("typeTourDTO",typeTourDTO);

        return "newTour";
    }
    @PostMapping("/saveTour")
    public String saveTour(){

        return "tour_subAdmin";
    }
    @PostMapping("/saveTypeTour")
    public String saveTypeTour(){

        return "tour_subAdmin";
    }

}
