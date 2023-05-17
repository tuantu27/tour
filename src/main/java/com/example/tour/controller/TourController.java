package com.example.tour.controller;

import com.example.tour.exception.CustomException;
import com.example.tour.model.dto.*;
import com.example.tour.service.*;
import com.example.tour.utils.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.tour.utils.Formatter;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class TourController {

    @Autowired
    private ITourService iTourService;
    @Autowired
    ITypeTourService iTypeTourService;

    @Autowired
    ICustomerService iCustomerService;

    @Autowired
    IBookingService iBookingService;

    @GetMapping(value = "/updateInfo")
    public String updateInfo(Model model ,@ModelAttribute("bookingDTO")BookingDTO bookingDTO){
        long total = 0;
        total = bookingDTO.getNumberAdult()*bookingDTO.getToursDTO().getPriceAdult()+
                bookingDTO.getNumberChildren()*bookingDTO.getToursDTO().getPriceChildren()+
                bookingDTO.getNumberInfant()*bookingDTO.getToursDTO().getPriceInfant();

        model.addAttribute("total",total);

        return "";
    }


    @GetMapping(value = "/tour_detail/{id}")
    public String detailTour(@PathVariable("id")Long id, Model model) throws CustomException {
        Formatter formatter = new Formatter();

        ToursDTO toursDTO = iTourService.getById(id);
        String x =  formatter.formatCurrency(String.valueOf(toursDTO.getPriceAdult()),"VND");
        String y =  formatter.formatCurrency(String.valueOf(toursDTO.getPriceChildren()),"VND");
        String z =  formatter.formatCurrency(String.valueOf(toursDTO.getPriceInfant()),"VND");
        toursDTO.setFormatPrice(x);
        toursDTO.setFormatPriceChil(y);
        toursDTO.setFormatPriceIn(z);
        InfoBookingDTO infoBookingDTO = new InfoBookingDTO();
        infoBookingDTO.setTourId(id);
        List<TypeTourDTO> lstTypeTour = iTypeTourService.getAll();
        model.addAttribute("infoBookingDTO",infoBookingDTO);
        model.addAttribute("lstTypeTour", lstTypeTour);
        model.addAttribute("tourDetail",toursDTO);
        return "tourDetail_Booking";
    }

    @PostMapping(value = "/booking")
    public RedirectView checkOut(@ModelAttribute ("infoBookingDTO")InfoBookingDTO infoBookingDTO, RedirectAttributes redirectAttributes, @RequestParam("payment")int methodPayment) {
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());
        CustomersDTO customersDTO = new CustomersDTO();
        customersDTO.setEmail(infoBookingDTO.getEmail());
        customersDTO.setFullName(infoBookingDTO.getFullName());
        customersDTO.setNumber_id(infoBookingDTO.getNumber_id());
        customersDTO.setPhoneNumber(infoBookingDTO.getPhoneNumber());
        customersDTO.setAddress(infoBookingDTO.getAddress());
        Long cus_id = iCustomerService.saveCusomer(customersDTO);
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setToursDTO(iTourService.getById(infoBookingDTO.getTourId()));
        bookingDTO.setBookingTime(timestamp2);
        bookingDTO.setNumberAdult(infoBookingDTO.getNumberAdult());
        bookingDTO.setNumberChildren(infoBookingDTO.getNumberChildren());
        bookingDTO.setNumberInfant(infoBookingDTO.getNumberInfant());
        bookingDTO.setTotalPrice(infoBookingDTO.getTotalPrice());
        if(methodPayment==1){
            bookingDTO.setPaymentMethod("Thanh toán trực tiếp");
        } else if (methodPayment == 2) {
            bookingDTO.setPaymentMethod("Chuyển khoản");
        }
        bookingDTO.setCustomersDTO(iCustomerService.getById(cus_id));
        Long booking_id = iBookingService.saveBooking(bookingDTO);
        redirectAttributes.addAttribute("cus_id",cus_id);
        redirectAttributes.addAttribute("booking_id",booking_id);

        return new RedirectView("show_bill");
    }
    @GetMapping(value = "/show_bill")
    public String showBill(@RequestParam("cus_id") Long cus_id,@RequestParam("booking_id") Long booking_id ,Model model) throws CustomException {
        Formatter formatter = new Formatter();
        BookingDTO bookingDTO = iBookingService.getById(booking_id);
        CustomersDTO customersDTO = iCustomerService.getById(cus_id);
        ToursDTO toursDTO = iTourService.getById(bookingDTO.getToursDTO().getTourId());
        String formatAllPrice =  formatter.formatCurrency(String.valueOf(bookingDTO.getTotalPrice()),"VND");
        bookingDTO.setFormatAllPrice(formatAllPrice);
        model.addAttribute("bookingDTO",bookingDTO);
        model.addAttribute("customersDTO",customersDTO);
        model.addAttribute("toursDTO",toursDTO);
        return "bill";
    }
}


