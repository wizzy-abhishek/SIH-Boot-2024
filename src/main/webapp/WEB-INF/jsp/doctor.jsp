<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Management</title>
    <link rel="stylesheet" href="doctor.css">
</head>
<body>
    <div class="container">
        <!--nav-->
    <nav class="navbar">
                  <ul class="nav-list">
                      <li>
                          <form action="home">
                              <button type="submit">Queue</button>
                          </form>
                      </li>
                      <li>
                          <form action="patient">
                              <button type="submit">Patient</button>
                          </form>
                      </li>
                      <li>
                          <form action="appointment">
                              <button type="submit">Appointment</button>
                          </form>
                      </li>
                      <li>
                          <form action="bed">
                              <button type="submit">Bed</button>
                          </form>
                      </li>
                      <li>
                          <form action="department">
                              <button type="submit">Department</button>
                          </form>
                      </li>
                      <li>
                          <form action="doctor">
                              <button type="submit">Doctors</button>
                          </form>
                      </li>
                      <li>
                          <form action="medicines">
                              <button type="submit">Inventory</button>
                          </form>
                      </li>
                      <li>
                          <form action="underConstruction">
                              <button type="submit">Emergency</button>
                          </form>
                      </li>
                  </ul>
              </nav>
        <!--nav-->

        <!-- Left Side: Appoint and Add Doctor Forms -->
        <div class="form-container left">

            <!-- Add New Doctor Form -->

            <form id="addDoc" action="addDoc" method="get" >
                <h2>Add New Doctor</h2>

                <label for="docName">Doctor Name:</label>
                <input type="text" id="docName" name="docName" required>

                <label for="doctorId">Doctor ID:</label>
                <input type="text" id="doctorId" name="doctorId" required>

                <label for="departments">Hospital Department:</label>
                    <select id="departments" name="departments" required>
                        <option value="">Select Department</option>
                        <c:forEach var="dept" items="${departmentList}">
                        <option value="${dept.name}">${dept.name}</option>
                        </c:forEach>
                    </select>

                <button type="submit">Add Doctor</button>
            </form>

            <!-- Appoint Doctor Form -->

            <form id="appointDoctorToPatient" action="appointDoctorToPatient" >
                <h2>Appoint a Doctor to Patient </h2>

                <label for="patient-id">Patient ID:</label>
                <input type="text" id="patient-id" name="patient-id" required>

                <label for="appoint-dr-id">Doctor ID:</label>
                <input type="text" id="appoint-dr-id" name="appoint-dr-id" required>

                <button type="submit">Appoint Doctor</button>
            </form>
        </div>

        <!-- Right Side: Search Doctor Form and Doctor Details -->

        <div class="form-container right">
            <form id="searchDoctor" action="searchDoctor" method="post" >
                <h2>Search for Doctor</h2>
                <label for="doctorId">Doctor ID:</label>
                <input type="text" id="doctorId" name="doctorId" required>
                <button type="submit">Search Doctor</button>
            </form>

            <!-- Doctor Details Section -->

            <div id="doctor-details">
                <h3>Doctor Details : </h3>
                <br>
                <p>Doctor ID : ${doctorDetails.id} </p>
                <p>Doctor Name : ${doctorDetails.name} </p>
                <p>Doctor Department : ${doctorDetails.dept.name} </p>

                <br>

                      <!-- Displaying Patients -->

                      <h3>Patients:</h3>
                      <c:forEach var="patient" items="${doctorDetails.patients}">
                          <p>Patient ID : ${patient.aadharNumber}</p>
                      </c:forEach>
                      <br>

                      <!-- Displaying Appointments -->

                      <h3>Appointments:</h3>
                      <c:forEach var="appointment" items="${doctorDetails.appointments}">
                          <p>Appointment ID : ${appointment.id}</p>
                          <p>Appointment Date : ${appointment.date}</p>
                      </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
