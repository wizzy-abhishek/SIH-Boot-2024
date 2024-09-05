package com.hospital.hospital.controller;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.service.AppointmentService;
import com.hospital.hospital.service.DepartmentService;
import com.hospital.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService ;

    @Autowired
    private DepartmentService departmentService ;

    @Autowired
    private AppointmentService appointmentService ;

    @GetMapping("doctor")
    public ModelAndView doctorPage(ModelAndView modelAndView){

        List<Department> departmentList = departmentService.getAllDepartments();
        modelAndView.addObject("departmentList" , departmentList );

        modelAndView.setViewName("doctor");

        return modelAndView ;

    }

    @GetMapping("addDoc")
    public ModelAndView addDoctor(@RequestParam("doctorId") String doctorId ,@RequestParam("docName") String docName ,@RequestParam("departments") Department departments , ModelAndView modelAndView ){

        boolean isAvailable = doctorService.isAvailable(doctorId);

        if(isAvailable){
            modelAndView.addObject("error" , "ID ALREADY ALLOTTED");
            modelAndView.setViewName("error");
            return modelAndView ;
        }

        doctorService.addNewDoctor(doctorId , docName , departments) ;
        modelAndView.setViewName("doctor");
        modelAndView.addObject("doctorDetails" , doctorService.findById(doctorId));

        return modelAndView ;
    }

    @PostMapping("searchDoctor")
    public ModelAndView searchDoctor(@RequestParam("doctorId") String doctorId , ModelAndView modelAndView){

        Doctor doctor = doctorService.findById(doctorId);

        System.out.println("DOC Controller Line 60 ");

        if(doctor == null){
            modelAndView.setViewName("error");
            modelAndView.addObject("error" , "NO SUCH DOCTOR");
            return modelAndView ;
        }

        System.out.println("DOC Controller Line 68 ");

        modelAndView.setViewName("doctor");
        modelAndView.addObject("doctorDetails" , doctorService.findById(doctorId));

        System.out.println("DOC Controller Line 73");

        System.out.println("Doctor's patients: " + doctor.getPatients());

        return modelAndView ;
    }

    @GetMapping("appointDoctorToPatient")
    public ModelAndView appointDoctorToPatient(@RequestParam("patient-id") String patientId,
                                               @RequestParam("appoint-dr-id") String doctorId,
                                               ModelAndView modelAndView) {
        try {
            boolean isAssigned = doctorService.assignDoctorToPatient(patientId, doctorId);

            if (isAssigned) {
                modelAndView.addObject("message", "Doctor assigned to patient successfully!");
                modelAndView.setViewName("success");
            } else {
                modelAndView.addObject("message", "Doctor is already assigned to this patient.");
                modelAndView.setViewName("info");
            }
        } catch (Exception e) {
            modelAndView.addObject("error", "An error occurred while assigning the doctor.");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }


}
