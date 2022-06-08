package com.example.accountingapp.controller;

import com.example.accountingapp.dto.PaymentDTO;
import com.example.accountingapp.entity.Payment;
import com.example.accountingapp.repository.PaymentRepository;
import com.example.accountingapp.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormatSymbols;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/list")
    public String selectPaymentYear(Model model, @RequestParam(defaultValue = "2022") String year){
        String[] month = new DateFormatSymbols().getMonths();
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("year", year);
        model.addAttribute("payments", paymentService.listAllByYear(year));

        return "/payment/payment-list";
    }
//    @GetMapping("/list")
//    public String paymentPage(Model model){
//        model.addAttribute("payments", paymentService.listAllPayments());
//        return "/payment/list";
//    }


//        List<PaymentDTO> payments = paymentService.listAllPayments();
//        model.addAttribute("payments", payments);
       // return "/payment/payment-list";
//    }

    @GetMapping("/payment-consent")
    public String paymentConsent(){
        return "/payment/payment-consent";
    }


    @GetMapping("/payment-success")
    public String paymentSuccess(){
        return "/payment/payment-success";
    }

    @GetMapping("/payment-success-print")
    public String paymentSuccessPrint(){
        return "/payment/payment-success-print";
    }
}
