package com.hospital.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnderConstructionController {

    @GetMapping("underConstruction")
    public String underConstruction(){
        return "underConstruction" ;
    }
}
