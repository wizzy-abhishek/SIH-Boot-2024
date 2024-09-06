<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Queue Management</title>
    <link rel="stylesheet" href="home.css">
</head>
<body>
    <div class="header-container">
        <h1><i class="fas fa-hospital"></i> Name of Hospital</h1>
    </div>

     <!-- Navigation Bar -->
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

    <!-- Main Content -->
    <div class="queue-container">
        <div class="queue-left">
            <h2>Select Department and Number of Doctors</h2>
            <form action="queue" id="queue" method="post">
                <label for="department">Department:</label>
                <select id="department" name="department" required>
                    <option value="" disabled selected hidden>Select Department</option>
                    <c:forEach var="dept" items="${departments}">
                        <option value="${dept.name}">${dept.name}</option>
                    </c:forEach>
                </select>

                <label for="doctors">Number of Doctors Available:</label>
                <select id="doctors" name="doctors" required>
                    <option value="1">1</option>
                    <option value="2" selected>2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
                <div class="button-group">
                    <button type="submit">Submit</button>
                </div>
            </form>

            <!-- Reset Department -->
            <div class="new-label">
                <h2>Reset Department</h2>
                <form action="reset" id="rest" method="get">
                    <label for="department">Department:</label>
                    <select id="department" name="department" required>
                        <option value="">Select Department</option>
                        <c:forEach var="dept" items="${departments}">
                            <option value="${dept.name}">${dept.name}</option>
                        </c:forEach>
                    </select>
                    <button type="submit">Reset</button>
                </form>
            </div>
        </div>

        <div class="queue-right">
            <h2>Room and Queue Information</h2>

            <label for="department-name">Department Name:</label>
            <input type="text" id="department-name" name="department-name" placeholder="${queueData['departmentName']}" readonly>

            <label for="room-number">Allotted Room Number:</label>
            <input type="text" id="room-number" name="room-number" placeholder="${queueData['assignedDoctor']}" readonly>

            <label for="queue-number">Queue Number:</label>
            <input type="text" id="queue-number" name="queue-number" placeholder="${queueData['queuePosition']}" readonly>
            <p> WE GONNA UPDATE THIS TOO BEFORE UPLOADING TO SIH WEBSITE , WE WILL EXPLAIN ALL APPROACHES AND EVERYTHING WHATEVER PPT DEMANDS HERE IN CREATIVE WAY TOO </p>
            <p>
            DEPT MAI DEPT ADD KR SKTE .
            PHIR DOCTOR ADD KRNA .
            PATIENT ADD KRNA .
            PATIENT KO DOCTOR APPOINT KR SKTE .
            PHIR USKO APPOINTMENT FIX KR SKTE .
            </p>
            <p>
            This is a prototype for SIH , MAIN VERSION WILL ON REACT AND WILL CONTAIN MORE FUNCTIONALITY
            </p>
        </div>

    </div>

    <!-- Reset All Button -->
    <div class="reset-all-container">
        <form action="reset_All" id="rest" method="get">
            <button type="submit" id="resetAll">Reset All</button>
        </form>
    </div>
</body>
</html>
