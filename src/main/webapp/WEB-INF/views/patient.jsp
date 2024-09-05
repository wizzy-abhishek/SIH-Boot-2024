<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <style>

    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Admission Form</title>
    <link rel="stylesheet" href="patient.css">
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
                       <form action="underConstruction">
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
        <div class="form-container">
            <u><b>
                    <h2>Patient Registration Form</h2>
                </b></u>
             <form action="patient" method="post">
                        <div class="form-group">
                            <label for="aadhaarNumber">Aadhaar Number:</label>
                            <input type="text" id="aadhaarNumber" name="aadhaarNumber" required>
                        </div>
                        <div class="form-group">
                            <label for="name">Name of Patient:</label>
                            <input type="text" id="name" name="name" required>
                        </div>
                        <div class="form-group">
                            <label for="mobile">Mobile Number:</label>
                            <input type="tel" id="mobile" name="mobile" required>
                        </div>
                        <div class="form-group">
                            <label for="date_of_Birth">Date of Birth:</label>
                            <input type="date" id="date_of_Birth" name="date_of_Birth" required>
                        </div>
                        <div class="form-group">
                            <label for="departmentList">Hospital Department:</label>
                            <select id="departmentList" name="departmentList" required>
                                <option value="">Select Department</option>
                                <c:forEach var="dept" items="${departments}">
                                    <option value="${dept.name}">${dept.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <button type="submit">Submit</button>
                        </div>
                    </form>
        </div>
        <div class="Patientdeatils">
            <form action="search_Patient">
            <div class="search">
                <input type="text" id="searchPatient" name="searchPatient" placeholder="Search by Patient ID">
            <button type="search">Search</button>
             </div>
            </form>
            <h3>Patient Infromation</h3>
            <div class="Patient" id="Patientdeatils">
                <!-- deatils ynh ayegi-->

                <p> PATIENT AADHAAR : ${patientDetail.aadharNumber} </p>
                <p> PATIENT NAME : ${patientDetail.name} </p>
                <p> PATIENT Number : ${patientDetail.mobile} </p>
                <p> PATIENT DOB : ${patientDetail.date_of_Birth} </p>

                <!-- Display list of departments -->
                <p>Departments:</p>
                <c:forEach var="patientDept" items="${patientDetail.departmentList}">
                <p>${patientDept.name}</p>
                </c:forEach>

                <br>

                <!-- Display list of doctors -->
                <p>Doctors:</p>
                <c:forEach var="patientDoc" items="${patientDetail.doctors}">
                <p>${patientDoc.id}</p>
                </c:forEach>
            </div>
        </div>
</div>
</body>

</html>
