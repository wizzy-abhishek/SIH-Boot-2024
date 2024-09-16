<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bed Management</title>
    <link rel="stylesheet" href="bed.css">
</head>
<body>
    <div class="container">
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

        <h2>General Ward - Bed Management</h2>
        <div class="bed-icons" id="bedIcons">
            <c:forEach var="bed" items="${beds}">
                <div class="bed-container ${bed.allotted ? 'occupied' : ''}"
                     data-bed-id="${bed.bedId}"
                     data-patient-id="${bed.patient != null ? bed.patient.aadharNumber : ''}">
                    Bed ${bed.bedId}
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="popup" id="popup">
        <form action="assignBed" method="post">
            <input type="hidden" id="bedId" name="bedId">
            <input type="text" id="patientId" name="patientId" placeholder="Enter Patient ID">
            <button type="submit">Assign Bed</button>
        </form>

        <form action="removePatient" method="post">
            <input type="hidden" id="bedIdRemove" name="bedId">
            <button type="submit" class="remove">Remove Patient</button>
        </form>
    </div><br><br>
    <p><b>UNDER DEVELOPMENT </b></p>
    <script>
        let selectedBed = null;

        document.querySelectorAll('.bed-container').forEach(bed => {
            bed.addEventListener('click', function() {
                selectedBed = this;
                document.getElementById('bedId').value = this.dataset.bedId;
                document.getElementById('bedIdRemove').value = this.dataset.bedId;
                document.getElementById('patientId').value = this.dataset.patientId;
                document.getElementById('popup').classList.add('active');
            });
        });

        function closePopup() {
            document.getElementById('popup').classList.remove('active');
        }
    </script>
</body>
</html>
