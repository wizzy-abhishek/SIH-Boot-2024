package com.hospital.hospital;

import com.hospital.hospital.model.Bed;
import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.model.Emergency;
import com.hospital.hospital.model.queueModel.Dept;
import com.hospital.hospital.service.BedService;
import com.hospital.hospital.service.DepartmentService;
import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.service.EmergencyService;
import com.hospital.hospital.service.queueService.DeptService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class HospitalApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HospitalApplication.class);
	}

	public static void main(String[] args) throws IOException {

		SpringApplication.run(HospitalApplication.class, args);

/*		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DepartmentService departmentService = context.getBean(DepartmentService.class);
		DoctorService doctorService = context.getBean(DoctorService.class);
		DeptService deptService = context.getBean(DeptService.class);
		BedService bedService = context.getBean(BedService.class);
		EmergencyService emergencyService = context.getBean(EmergencyService.class);

		while(true){
			System.out.println("Enter Input\nBed\nDoctor\nDepartment\nEmergency\nAppointment\nExit");
			String userInput = br.readLine();
			if (userInput.equalsIgnoreCase("EXIT")){
				break;
			}else if (userInput.equalsIgnoreCase("Bed")){
				Bed bed = context.getBean(Bed.class);
				System.out.println("Enter BED ID ");
				String bedId = br.readLine();
				bed.setBedId(bedId);
				bedService.saveBed(bed);
				System.out.println("Done");
			} else if (userInput.equalsIgnoreCase("Department")) {
				Department department = context.getBean(Department.class);
				Dept dept = context.getBean(Dept.class);

				System.out.println("Enter Dept name ");
				String deptName = br.readLine();

				department.setName(deptName);
				dept.setName(deptName);

				departmentService.addDepartment(department);
				deptService.saveDept(dept);

				System.out.println("Done");

			} else if (userInput.equalsIgnoreCase("DOCTOR")) {

				Doctor doctor = context.getBean(Doctor.class);

				System.out.println("Enter Doctors Id ");
				String doctorId = br.readLine();
				doctor.setId(doctorId);

				System.out.println("Enter name ");
				String docName = br.readLine();
				doctor.setName(docName);

				System.out.println(departmentService.getAllDepartments());

				System.out.println("Enter dept");
				String departmentName = br.readLine();

				Department department = departmentService.findById(departmentName);

				doctor.setDept(department);

				doctorService.saveDoctor(doctor);

				department.getDoctors().add(doctor);

				departmentService.addDepartment(department);

				System.out.println("Done");

			} else if (userInput.equalsIgnoreCase("Emergency")) {

				Emergency emergency = context.getBean(Emergency.class);

				System.out.println("Enter e-Bed id");
				String eBed = br.readLine();
				emergency.seteBedId(eBed);

				emergencyService.add_E_bed(emergency);

			}
			else{
				System.out.println("Enter any of the above");
			}

		}


	}*/

	}
}
