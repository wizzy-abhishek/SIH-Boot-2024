<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment Page with Allotments and Search</title>
    <link rel="stylesheet" href="appointment.css">
</head>

<body>
    <div class="container">
        <!--nav bar-->
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
                    <form action="Department.jsp">
                        <button type="submit">Department</button>
                    </form>
                </li>
                <li>
                    <form action="Doctors.jsp">
                        <button type="submit">Doctors</button>
                    </form>
                </li>
                <li>
                    <form action="Inventory.jsp">
                        <button type="submit">Inventory</button>
                    </form>
                </li>
                <li>
                    <form action="Emergency.jsp">
                        <button type="submit">Emergency</button>
                    </form>
                </li>
            </ul>
        </nav>

        <!-- Form for booking an appointment -->
        <div class="form-section">
            <h2>Book an Appointment</h2>
            <form action="appointmentForm" id="appointmentForm" method="post">
                <label for="date">Appointment Date:</label>
                <input type="date" id="date" name="date" pattern="\d{4}-\d{2}-\d{2}" required>

                <label for="patient-id">Patient ID:</label>
                <input type="text" id="patient-id" name="patient_id" placeholder="Enter Patient ID" required>

                <label for="doctor-name">Doctor's ID:</label>
                <input type="text" id="doctor_Id" name="doctor_Id" placeholder="Enter Doctor's ID" required>

                <button type="submit">Book Appointment</button>
            </form>
            <img src="appointment.avif" alt="">
        </div>

        <!-- Allotments Section -->
        <div class="allotments-section">
            <div class="search">
                <div class="search-bar">
                    <input type="text" id="searchPatient" placeholder="Search by Patient ID">
                </div>
                <button type="search">Search</button>
            </div>

            <h3>Allotted Appointments</h3>
            <div class="allotments" id="allotmentsList">
                <!-- Display appointment details using EL -->
                <p>Appointment ID: ${appointmentBooked.id}</p>
                <p>Date: ${appointmentBooked.date}</p>
                <p>Patient Aadhaar Number: ${appointmentBooked.patient.aadharNumber}</p>

                <!-- Display list of departments -->
                <p>Departments:</p>
                <c:forEach var="dept" items="${appointmentBooked.departmentList}">
                    <p>${dept.name}</p>
                </c:forEach>

                <br>

                <!-- Display list of doctors -->
                <p>Doctors:</p>
                <c:forEach var="doc" items="${appointmentBooked.doctorList}">
                    <p>${doc.id}</p>
                </c:forEach>
            </div>
        </div>
    </div>
</body>

</html>
