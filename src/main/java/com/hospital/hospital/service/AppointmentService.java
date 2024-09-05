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
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DoctorService doctorService;

    @Transactional
    public synchronized Appointment bookAppointment(String date, String patientId, String doctorId) {

        Appointment appointment = new Appointment();
        appointment.setDate(date);

        // Find patient and doctor
        Patient patient = patientService.findPatientById(patientId);
        Doctor doctor = doctorService.findById(doctorId);

        if (patient == null ){
            System.err.println("Patient doesn't exist.");
            return null;
        }

        if( doctor == null){
            System.err.println("Doctor doesn't exist.");
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
            System.err.println("Error while booking appointment: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return appointmentRepo.findById(uniqueId).orElse(null);
    }

    @Transactional
    public synchronized void deleteAppointmentById(String id) {
        try {
            appointmentRepo.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error while deleting appointment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public synchronized boolean isAppointmentExistForPatient(String patientId) {
        try {
            return appointmentRepo.existsByPatient_AadharNumber(patientId);
        } catch (Exception e) {
            System.err.println("Error checking if appointment exists for patient ID: " + e.getMessage());
            throw new RuntimeException("Failed to check if appointment exists for patient ID", e);
        }
    }

    @Transactional(readOnly = true)
    public synchronized List<Appointment> findAppointmentByPatientId(String patientId) {
        try {
            return appointmentRepo.findByPatient_AadharNumber(patientId);
        } catch (Exception e) {
            System.err.println("Error finding appointments by patient ID: " + e.getMessage());
            throw new RuntimeException("Failed to find appointments by patient ID", e);
        }
    }

    public Appointment findByObj(Appointment appointment) {
        try {
            return appointmentRepo.findById(appointment.getId()).orElse(null);
        } catch (Exception e) {
            System.err.println("Error while finding appointment: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public synchronized void updateAppointmentDate(String id, String date) {
        try {
            Appointment appointment = appointmentRepo.findById(id).orElse(null);
            if (appointment != null) {
                appointment.setDate(date);
                appointmentRepo.save(appointment);
            } else {
                System.err.println("Appointment not found for ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error while updating appointment date: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
