package com.example.accountingapp.controller;

import com.example.accountingapp.dto.ClientVendorDTO;
import com.example.accountingapp.service.ClientVendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client-vendor")
public class ClientVendorController {

    private final ClientVendorService clientVendorService;

    public ClientVendorController(ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @GetMapping("/add")
    public String createClient(Model model) {
        model.addAttribute("client", new ClientVendorDTO());
        return "/clientvendor/client-vendor-add";
    }

    @PostMapping("/add")
    public String insertClient(@ModelAttribute("client") ClientVendorDTO client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("client", new ClientVendorDTO());
            return "/clientvendor/client-vendor-add";
        }
        //clientVendorService.save(client);
        return "redirect:/client-vendor/list";
    }

    @GetMapping("/list")
    public String clientList(Model model) {
        model.addAttribute("clients", clientVendorService.listAllClients());
        return "/clientvendor/client-vendor-list";
    }

    @GetMapping("/edit/{id}") //
    public String updateClient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientVendorService.findById(id));
        return "/clientvendor/client-vendor-edit";
    }

    @PostMapping("/edit")
    public String editClient(@ModelAttribute("client") ClientVendorDTO client, BindingResult bindingResult,Model model) {

        if (bindingResult.hasErrors()) {
            return "/clientvendor/client-vendor-edit";
        }
        // clientVendorService.update(client);
        return "redirect:/client-vendor/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        clientVendorService.delete(id);
        return "redirect:/client-vendor/list";
    }
}