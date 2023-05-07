package com.example.tour.controller;

import com.example.tour.config.MvcConfig;
import com.example.tour.entity.AccountsEntity;
import com.example.tour.model.dto.*;
import com.example.tour.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    ICompanyService iCompanyService;

    @GetMapping("/home")
    public String viewHome() {

        return "subadmin";
    }
    @GetMapping("/tour")
    public String viewTour(HttpSession session,Model model) {
        AccountsEntity accountsEntity = (AccountsEntity) session.getAttribute("user");
        List<ToursDTO> toursDTOS = iTourService.getToursByAccountId(accountsEntity.getAccountId());
        model.addAttribute("lstTour", toursDTOS);
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

        return "redirect:/subAdmin/tour";
    }
    @PostMapping("/saveTypeTour")
    public String saveTypeTour(@ModelAttribute("typeTourDTO")TypeTourDTO typeTourDTO){
        iTypeTourService.saveTypeTour(typeTourDTO);
        return "redirect:/subAdmin/tour";
    }

    @GetMapping(value = "/edit-Tour/{id}")
    public String editProduct(Model model, @PathVariable("id")Long id){
        ToursDTO toursDTO = iTourService.getById(id);
        List<TypeTourDTO> lstTypeTour = iTypeTourService.getAll();
        List<TypeTourDTO> lstCurTypeTour = iTypeTourService.getLstByTourId(id);
        TypeTourDTO typeTourDTO = new TypeTourDTO();
        model.addAttribute("tourDTO",toursDTO);
        model.addAttribute("lstTypeTour", lstTypeTour);
        model.addAttribute("lstCurTypeTour", lstCurTypeTour);
        model.addAttribute("typeTourDTO",typeTourDTO);
        return "editTour";

    }
    @PostMapping("/updateTour")
    public String updateTour(@ModelAttribute("tourDTO")ToursDTO toursDTO,@RequestParam("newImage") MultipartFile file
            ,@RequestParam(value = "destination",required = false)Long[] lstTypeTourId,HttpSession session){
        AccountsEntity accountsEntity = (AccountsEntity) session.getAttribute("user");
        toursDTO.setAccountId(accountsEntity.getAccountId());
        String nameFile = mvcConfig.uploadImages(file);
        toursDTO.setImgName(nameFile);
        iTourService.updateTour(toursDTO);
        Long[] arrTypeTourId = lstTypeTourId;
        if(arrTypeTourId.length > 0){
            List arr = Arrays.asList(arrTypeTourId);
            List<TypeTourDTO> lstCurTypeTour = iTypeTourService.getLstByTourId(toursDTO.getTourId());
            List currArrId = new ArrayList<>();
            //xoa cac destination khong duoc select
            for(int i =0 ; i<lstCurTypeTour.size();i++){
                if(arr.contains(lstCurTypeTour.get(i).getTypeTourId())){
                    currArrId.add(lstCurTypeTour.get(i).getTypeTourId());
                    continue;
                }else if(!arr.contains(lstCurTypeTour.get(i).getTypeTourId())) {

                    Long currTT =lstCurTypeTour.get(i).getTypeTourId();
                    iMultipleTypeTourService.deleteMultipleTT(toursDTO.getTourId(),currTT);
                }
            }
            //them cac destination moi
            for(int j = 0 ;j<arr.size()-1;j++){
                if(!currArrId.contains(arr.get(j+1))){
                    MultipleTypeTourDTO temp = new MultipleTypeTourDTO();
                    temp.setToursDTO(toursDTO);
                    temp.setTypeTourDTO(iTypeTourService.getById((Long)arr.get(j+1)));
                    iMultipleTypeTourService.saveMultipleTT(temp);
                }

            }
        }

        return "redirect:/subAdmin/tour";
    }

    @GetMapping(value = "/delete-Tour/{id}")
    public String deleteProduct(Model model, @PathVariable("id")Long id){
        iTourService.deleteTour(id);
        return "redirect:/subAdmin/tour";

    }

    //company
    @GetMapping(value = "/edit-Profile/{id}")
    public String editProfile(Model model, @PathVariable("id")Long id,HttpSession session){
        AccountsEntity accountsEntity = (AccountsEntity) session.getAttribute("user");
        CompanysDTO companysDTO = iCompanyService.getByAccountId(accountsEntity.getAccountId());
        model.addAttribute("company",companysDTO);
        return "editProfile";

    }
    @PostMapping(value = "/updateProfile")
    public String updateCompany(Model model,@ModelAttribute("company")CompanysDTO companysDTO){
        iCompanyService.updateProfile(companysDTO);
        return "redirect:/subAdmin/tour";

    }

    @GetMapping(value = "/review-Tour/{id}")
    public String reviewTour(Model model,@PathVariable("id") Long id){
        ToursDTO toursDTO = iTourService.getById(id);
        model.addAttribute("toursDTO",toursDTO);
        return "reviewTour";

    }

}
