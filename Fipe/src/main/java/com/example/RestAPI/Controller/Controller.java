package com.example.RestAPI.Controller;

import com.example.RestAPI.Service.FipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fipe")
public class Controller {

    @Autowired
    private FipeService fipeService;

    @GetMapping("/informacoes")
    public String obterInformacoesFipe(){
        return fipeService.obterInformacoesFipe();
    }
}
