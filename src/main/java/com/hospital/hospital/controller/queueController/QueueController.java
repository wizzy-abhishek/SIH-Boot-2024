package com.hospital.hospital.controller.queueController;

import com.hospital.hospital.service.queueService.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@Controller
public class QueueController {

    @Autowired
    private QueueService queueService;

    @PostMapping("/queue")
    public ModelAndView handleQueueRequest(@RequestParam("num_of_doctor") int num_of_doctor,
                                           @RequestParam("dept_Name") String dept_Name , ModelAndView modelAndView) throws IOException {
        Map<String, Object> queueData = queueService.queueImplement(num_of_doctor, dept_Name);
        modelAndView.setViewName("");
        modelAndView.addObject("queueData" , queueData);
        return modelAndView;
    }

    @DeleteMapping("Reset_All")
    public ModelAndView deleteAllQueue(ModelAndView modelAndView){
        queueService.resetAllPatientNumbers();
        modelAndView.setViewName("success");
        return modelAndView ;
    }
}
