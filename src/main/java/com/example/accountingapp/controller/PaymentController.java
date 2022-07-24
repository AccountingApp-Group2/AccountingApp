package com.example.accountingapp.controller;

import com.example.accountingapp.dto.PaymentDTO;
import com.example.accountingapp.service.CompanyService;
import com.example.accountingapp.service.PaymentService;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormatSymbols;
import java.time.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {
  private final PaymentService paymentService;
  private final CompanyService companyService;

  @Value("${stripe.public.key}")
  private String stripePublicKey;

  public PaymentController(PaymentService paymentService, CompanyService companyService) {
    this.paymentService = paymentService;
    this.companyService = companyService;
  }

  @GetMapping("/list")
  public String selectPaymentYear(Model model, @RequestParam(defaultValue = "2022") String year) {
    String[] month = new DateFormatSymbols().getMonths();
    model.addAttribute("localDateTime", LocalDateTime.now());
    model.addAttribute("year", year);
    model.addAttribute("payments", paymentService.listAllByYearAndCompany(year));

    return "/payment/payment-list";
  }

  @GetMapping("/payment-consent/{id}")
  public String paymentConsent(@PathVariable("id") Long id, Model model) {
    PaymentDTO paymentDTO = paymentService.findById(id);

    model.addAttribute("payment", paymentDTO);
    model.addAttribute("company", companyService.findById(id));
    return "/payment/payment-consent";
  }

  @GetMapping("/edit/{id}")
  public String editPayment(@PathVariable("id") Long id, Model model) {
    PaymentDTO paymentDTO = paymentService.findById(id);
    model.addAttribute("payment", paymentDTO);
    model.addAttribute("company", companyService.findById(id));
    return "/payment/payment";
  }

  @PostMapping("/edit/{id}")
  public String updatePayment() {
    return "/payment/payment";
  }

  @GetMapping("/newpayment/{id}")
  public String getPaymentMethod(Model model, @PathVariable("id") Long id) {

    PaymentDTO payment = paymentService.findPaymentById(id);
    if (payment.getIsPaid()) {
      return "redirect:/payment/list";
    }
    model.addAttribute("amount", payment.getAmount());

    model.addAttribute("id", id);
    model.addAttribute("currency", "USD");
    model.addAttribute("stripePublicKey", stripePublicKey);

    return "/payment/payment-checkout";
  }

  @PostMapping("/charge/{id}")
  public String chargePayment(@PathVariable("id") Long id) {

    paymentService.chargePaymentById(id);
    return "redirect:/payment/toInvoice/" + id;

  }


  @GetMapping("/toInvoice/{id}")
  public String paymentSuccess(@PathVariable("id") Long id, Model model) {

    PaymentDTO paymentDTO = paymentService.findById(id);

    model.addAttribute("payment", paymentDTO);
    model.addAttribute("company", companyService.findById(id));

    return "/payment/payment-success";
  }
}
