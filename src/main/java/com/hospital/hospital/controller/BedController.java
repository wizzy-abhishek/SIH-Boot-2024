package com.hospital.hospital.controller;

import com.hospital.hospital.model.Bed;
import com.hospital.hospital.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BedController {

    @Autowired
    private BedService bedService;

    @GetMapping("/bed")
    public String showBedManagementPage(Model model) {
        List<Bed> beds = bedService.findAll();
        model.addAttribute("beds", beds);
        return "bed";
    }

    @PostMapping("/assignBed")
    public String assignBed(@RequestParam String bedId, @RequestParam String patientId) {

        return "redirect:/bed";
    }

    @PostMapping("/removePatient")
    public String removePatient(@RequestParam String bedId) {
        Bed bed = bedService.findBedById(bedId);
        if (bed != null) {
            bed.setPatient(null);
            bed.setAllotted(false);
            bedService.saveBed(bed);
        }
        return "redirect:/beds";
    }
}

