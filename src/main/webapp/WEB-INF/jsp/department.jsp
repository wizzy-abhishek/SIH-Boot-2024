<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Department Management</title>
    <link rel="stylesheet" href="department.css">
</head>
<body>
    <div class="main">
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

        <!-- Left Section -->
        <div class="left-section section">
            <h2>Add New Department</h2>
            <form id="add-department-form" action="addDepartment" method="post" >
                <label for="department-name">Department Name:</label>
                <input type="text" id="dName" name="dName" placeholder="Enter department name" required>
                <button type="submit">Submit</button>
            </form>
        </div>

 <!-- Right Section -->
 <div class="right-section section">
     <div class="search-section">
         <h2>Search Department</h2>
         <form id="getDetails" action="${pageContext.request.contextPath}/getDetails" method="post">
             <label for="departmentList">Hospital Department:</label>
             <select id="departmentList" name="departmentList" required>
                 <option value="">Select Department</option>
                 <c:forEach var="dept" items="${departments}">
                     <option value="${dept.name}">${dept.name}</option>
                 </c:forEach>
             </select>
             <button type="submit">Search</button>
         </form>
     </div>

  <!-- Department Details Section -->
  <div class="department-details">
      <h3>Department Details</h3>
      <p>Department Name: ${departmentDetails.name}</p>
      <br>
      <p>Number of Doctors in Department: ${numOfDoctors}</p>
      <br>
      <p>Number of Patients in Department: ${numOfPatient}</p>
      <br>
      <p>All Doctor IDs:</p>
      <ul>
          <c:forEach var="doctorId" items="${departmentDetails.doctors}">
              <li>${doctorId.id}</li>
          </c:forEach>
      </ul>
  </div>
 </div>

</body>
</html>
