package com.hospital.hospital.service;

import com.hospital.hospital.model.Appointment;
import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.model.Patient;
import com.hospital.hospital.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
    public Appointment bookAppointment(String date, String patientId, String doctorId) {

        Appointment appointment = new Appointment();
        appointment.setDate(date);

        // Find patient and doctor
        Patient patient = patientService.findPatientById(patientId);
        Doctor doctor = doctorService.findById(doctorId);

        if (patient == null || doctor == null) {
            // Either patient or doctor doesn't exist, return null or throw exception
            return null;
        }

        // Setting patient and doctor for the appointment
        appointment.setPatient(patient);
        appointment.getDoctorList().add(doctor);

        Department department = doctor.getDept();
        appointment.getDepartmentList().add(department);

        // Check if the department and doctor are already associated with the patient
        if (!patient.getDepartmentList().contains(department)) {
            patient.getDepartmentList().add(department);
        }

        if (!patient.getDoctors().contains(doctor)) {
            patient.getDoctors().add(doctor);
        }

        // Add the appointment to the patient's appointments
        patient.getAppointments().add(appointment);

        // Set a unique ID for the appointment
        String uniqueId = patient.getAadharNumber() + doctor.getId() + UUID.randomUUID().toString().substring(0, 8);
        appointment.setId(uniqueId);

        try {
            // Save appointment, patient, doctor, and department
            appointmentRepo.save(appointment);

            doctor.getAppointments().add(appointment);
            department.getAppointments().add(appointment);

            departmentService.addDepartment(department);
            doctorService.saveDoctor(doctor);
            patientService.savePatientByObjectForAppointment(patient);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }

        return appointmentRepo.findById(uniqueId).orElse(null);
    }

    @Transactional
    public void deleteAppointmentById(String id){
        appointmentRepo.deleteById(id);
    }

    public Appointment findByObj(Appointment appointment){
        return appointmentRepo.findById(appointment.getId()).orElse(null);
    }

    @Transactional
    public void updateAppointmentDate(String id , String date){
        Appointment appointment = appointmentRepo.findById(id).orElse(null);
        assert appointment != null;
        appointment.setDate(date);
        appointmentRepo.save(appointment);
    }

}
