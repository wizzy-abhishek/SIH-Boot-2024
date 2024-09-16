<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medicine Management</title>
    <link rel="stylesheet" href="medicines.css">
</head>
<body>
    <div class="main-container">
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
        <!-- Left Container-->
         <div class="lr-container">
        <div class="left-container">

            <h2>Add Quantity Of Meds </h2>

            <form action="quantityOfMeds" method="post" id="addMedicineForm">

                <label for="medicineName">Medicine Name:</label>
                <select id="ListOfMeds" name="ListOfMeds" required>
                    <option value="">Select Medicine</option>
                    <c:forEach var="meds" items="${ListOfMeds}">
                        <option value="${meds.meds_name}">
                            ${meds.meds_name} - ${meds.quantity}
                        </option>
                    </c:forEach>
                </select>

                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" placeholder="Enter quantity" required>

                <button type="submit">Add Medicine</button>
            </form>

            <!-- add new med-->

            <h2>Add New Medicine </h2>

            <form action="newArrivedMeds" method="post" id="Medicinenewarrival">

                <label for="meds">Medicine New Arrival</label>
                <input type="text" id="MedicineName" name="MedicineName" placeholder="Enter Medicine name " required>

                <button type="submit">Add medicine</button>
            </form>

        </div>

        <!-- Right Container-->
        <div class="right-container">

            <h2>Allot Medicine to Patient</h2>

            <form action="MedsToPatient" method="post" id="allotMedicineForm">

                <label for="patientId">Patient ID:</label>
                <input type="text" id="patientId" name="patientId" placeholder="Enter patient ID" required>

                <label for="selectMedicine">Select Medicine:</label>
                <select id="ListOfMeds" name="ListOfMeds" required>
                    <option value="">Select Medicine</option>
                    <c:forEach var="meds" items="${ListOfMeds}">
                        <option value="${meds.meds_name}">
                            ${meds.meds_name} - ${meds.quantity}
                        </option>
                    </c:forEach>
                </select>

                <label for="allotQuantity">Quantity:</label>
                <input type="number" id="allotQuantity" name="allotQuantity" placeholder="Enter quantity" required>

                <button type="submit">Allot Medicine</button>
            </form>
        </div>
    </div>
    </div>
</body>
</html>
