package com.example.tour.controller;

import com.example.tour.exception.CustomException;
import com.example.tour.model.dto.ToursDTO;
import com.example.tour.model.dto.TypeTourDTO;
import com.example.tour.service.ITourService;
import com.example.tour.service.ITypeTourService;
import com.example.tour.utils.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ITourService iTourService;
    @Autowired
    ITypeTourService iTypeTourService;

    @GetMapping(value = "/home")
    public String showHomePage(Model model) throws CustomException {
        List<ToursDTO> lstTour =iTourService.getAll();
        Formatter formatter = new Formatter();
        for (ToursDTO tour : lstTour){
           String x =  formatter.formatCurrency(String.valueOf(tour.getPriceAdult()),"VND");
            tour.setFormatPrice(x);
        }
        List<TypeTourDTO> lstTypeTour = iTypeTourService.getAll();

        model.addAttribute("lstTour", lstTour);
        model.addAttribute("lstTypeTour", lstTypeTour);
        return "home";
    }
}
