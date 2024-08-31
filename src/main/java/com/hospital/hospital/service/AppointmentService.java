package com.hospital.hospital.service;

import com.hospital.hospital.model.Appointment;
import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.model.Patient;
import com.hospital.hospital.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo ;

    @Autowired
    private PatientService patientService ;

    @Autowired
    private DepartmentService departmentService ;

    @Autowired
    private DoctorService doctorService ;

    @Transactional
    public void bookAppointment(Appointment appointment) throws ParseException {

        Patient patient = patientService.findById(appointment.getPatient().getAadharNumber());
        patient.getAppointments().add(appointment);
        patient.getDoctors().add(appointment.getDoctorList().getFirst());

        Doctor doctor = appointment.getDoctorList().getFirst() ;
        doctor.getAppointments().add(appointment);

        Department department = doctor.getDept();
        department.getAppointments().add(appointment);

        String uniqueId = patient.getAadharNumber() + doctor.getId() + UUID.randomUUID().toString().substring(0, 8);
        appointment.setId(uniqueId);

        appointmentRepo.save(appointment);
        departmentService.addDepartment(department);
        patientService.patientSave(patient);
        doctorService.saveDoctor(doctor);
    }

    @Transactional
    public void deleteAppointmentById(String id){
        appointmentRepo.deleteById(id);
    }

    public Appointment findByObj(Appointment appointment){
        return appointmentRepo.findById(appointment.getId()).orElse(null);
    }

}
