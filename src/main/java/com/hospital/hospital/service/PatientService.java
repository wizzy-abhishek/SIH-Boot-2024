package com.hospital.hospital.service;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Medicines;
import com.hospital.hospital.model.Patient;
import com.hospital.hospital.model.PatientAssignedMeds;
import com.hospital.hospital.repo.PatientAssignedMedsRepo;
import com.hospital.hospital.repo.PatientRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepo patientRepo;

    private final DepartmentService deptService;

    private final MedicineService medicineService ;

    private final PatientAssignedMedsRepo patientAssignedMedsRepo ;

    public PatientService(PatientRepo patientRepo, DepartmentService deptService, DoctorService doctorService, MedicineService medicineService, PatientAssignedMedsRepo patientAssignedMedsRepo) {
        this.patientRepo = patientRepo;
        this.deptService = deptService;
        this.medicineService = medicineService;
        this.patientAssignedMedsRepo = patientAssignedMedsRepo;
    }

    // Synchronized method to ensure thread safety
    @Transactional
    public synchronized Patient addNewPatient(String aadhaar, String name, String mobile, String dob, Department department) {
        Patient patient = new Patient();
        try {
            patient.setDates(String.valueOf(new Date()));

            System.out.println("Enter DOB");
            patient.setDate_of_Birth(dob);

            System.out.println("Enter Aadhaar number");
            patient.setAadharNumber(aadhaar);

            System.out.println("Enter name");
            patient.setName(name);

            System.out.println("Enter mobile");
            patient.setMobile(mobile);

            System.out.println("Enter the Dept for check-up");
            patient.getDepartmentList().add(department);

            patientRepo.save(patient);

            department.getPatients().add(patient);
            deptService.addDepartment(department);

        } catch (Exception e) {

            System.err.println("Error adding new patient: " + e.getMessage());
            throw new RuntimeException("Failed to add new patient", e);
        }
        return patientRepo.findById(aadhaar).orElse(null);
    }

    public void savePatientByObjectForAppointment(Patient patient) {
        try {
            patientRepo.save(patient);
        } catch (Exception e) {
            System.err.println("Error saving patient for appointment: " + e.getMessage());
            throw new RuntimeException("Failed to save patient for appointment", e);
        }
    }

    public List<Patient> findByMobile(String mobile) {
        try {
            return patientRepo.findByMobile(mobile);
        } catch (Exception e) {
            System.err.println("Error finding patient by mobile: " + e.getMessage());
            throw new RuntimeException("Failed to find patient by mobile", e);
        }
    }

    public boolean isPatientExists(String aadhaar) {
        try {
            return patientRepo.existsById(aadhaar);
        } catch (Exception e) {
            System.err.println("Error checking if patient exists: " + e.getMessage());
            throw new RuntimeException("Failed to check if patient exists", e);
        }
    }

    public List<Patient> findByName(String name) {
        try {
            return patientRepo.findByName(name);
        } catch (Exception e) {
            System.err.println("Error finding patient by name: " + e.getMessage());
            throw new RuntimeException("Failed to find patient by name", e);
        }
    }

    public Patient findPatientById(String aadhar) {
        try {
            return patientRepo.findById(aadhar).orElse(null);
        } catch (Exception e) {
            System.err.println("Error finding patient by ID: " + e.getMessage());
            throw new RuntimeException("Failed to find patient by ID", e);
        }
    }

    public List<Patient> findAll() {
        try {
            return patientRepo.findAll();
        } catch (Exception e) {
            System.err.println("Error finding all patients: " + e.getMessage());
            throw new RuntimeException("Failed to find all patients", e);
        }
    }

    public Patient updatePatient(Patient patient) {
        try {
            return patientRepo.save(patient);
        } catch (Exception e) {
            System.err.println("Error updating patient: " + e.getMessage());
            throw new RuntimeException("Failed to update patient", e);
        }
    }

    public void delete(String pId) {
        try {
            patientRepo.deleteById(pId);
        } catch (Exception e) {
            System.err.println("Error deleting patient by ID: " + e.getMessage());
            throw new RuntimeException("Failed to delete patient by ID", e);
        }
    }

    public void deleteAll() {
        try {
            patientRepo.deleteAll();
        } catch (Exception e) {
            System.err.println("Error deleting all patients: " + e.getMessage());
            throw new RuntimeException("Failed to delete all patients", e);
        }
    }


    public void assignMedsToPatient(String patientId, String medicinesName, Integer quantity) {

        // Find patient by ID

        System.out.println("In ASSIGN MEDS TO PATIENT METHOD ");

        Patient patient = findPatientById(patientId);

        // Find medicine by name
        Medicines meds = medicineService.findByIdByName(medicinesName);

        // Check if enough stock is available
        if (meds.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock for medicine: " + medicinesName);
        }

        // Create a new entry in PatientAssignedMeds
        PatientAssignedMeds patientAssignedMeds = new PatientAssignedMeds();

        patientAssignedMeds.setPatient(patient);
        patientAssignedMeds.setMedicines(meds);
        patientAssignedMeds.setQuantity(quantity);
        patientAssignedMeds.setLocalDateTime(LocalDateTime.now());

        // Save the assigned medicine entry
        patientAssignedMedsRepo.save(patientAssignedMeds);

        // Update global medicine stock
        meds.setQuantity(meds.getQuantity() - quantity);
        medicineService.saveMeds(meds);
    }

    public List<PatientAssignedMeds> getPatientMeds(String patientId) {
        // Fetch all medicines assigned to a specific patient
        return patientAssignedMedsRepo.findByPatientAadharNumber(patientId);
    }


}
