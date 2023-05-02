package com.example.tour.controller;

import com.example.tour.config.MvcConfig;
import com.example.tour.entity.AccountsEntity;
import com.example.tour.model.dto.AccountsDTO;
import com.example.tour.model.dto.MultipleTypeTourDTO;
import com.example.tour.model.dto.ToursDTO;
import com.example.tour.model.dto.TypeTourDTO;
import com.example.tour.service.IAccountService;
import com.example.tour.service.IMultipleTypeTourService;
import com.example.tour.service.ITourService;
import com.example.tour.service.ITypeTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/subAdmin")
public class SubAdminController {
    @Autowired
    ITypeTourService iTypeTourService;

    @Autowired
    IMultipleTypeTourService iMultipleTypeTourService;
    @Autowired
    ITourService iTourService;

    @Autowired
    MvcConfig mvcConfig;

    @Autowired
    IAccountService iAccountService;

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
    public String saveTour(@ModelAttribute("tourDTO")ToursDTO toursDTO,@RequestParam("newImage") MultipartFile file
            ,@RequestParam("destination")Long[] lstTypeTourId,HttpSession session){
        AccountsEntity accountsEntity = (AccountsEntity) session.getAttribute("user");
        String nameFile = mvcConfig.uploadImages(file);
        toursDTO.setImgName(nameFile);
        toursDTO.setAccountId(accountsEntity.getAccountId());
        Long tourId = iTourService.saveTour(toursDTO);
        ToursDTO toursDTO1 = iTourService.getById(tourId);
        Long[] arrTypeTourId = lstTypeTourId;
        for (int i = 0; i<arrTypeTourId.length;i++){
            MultipleTypeTourDTO temp = new MultipleTypeTourDTO();

            temp.setToursDTO(toursDTO1);
            temp.setTypeTourDTO(iTypeTourService.getById(arrTypeTourId[i]));
            iMultipleTypeTourService.saveMultipleTT(temp);
        }

        return "tour_subAdmin";
    }
    @PostMapping("/saveTypeTour")
    public String saveTypeTour(@ModelAttribute("typeTourDTO")TypeTourDTO typeTourDTO){
        iTypeTourService.saveTypeTour(typeTourDTO);
        return "tour_subAdmin";
    }

}
