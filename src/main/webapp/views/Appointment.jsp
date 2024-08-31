<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment Page with Allotments and Search</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #ffffff;
            margin: 0;
            padding: 0;
            width: 100vw;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .container {
            background-color: #fff;
            width: 100%;
            max-width: 1450px;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            min-height: 90%;
            position: relative;
        }
        .form-section, .allotments-section {
            flex: 1 1 45%;
            margin: 20px 0;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
        }
        .form-section h2, .allotments-section h3 {
            text-align: center;
            color: #333;
        }
        .form-section label, .form-section input, .form-section button {
            display: block;
            width: 100%;
            margin-bottom: 15px;
            font-size: 16px;
        }
        .form-section input, .form-section button {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .form-section button {
            background-color: #4CAF50;
            color: white;
            font-size: 18px;
            cursor: pointer;
            border: none;
        }
        img{
            width: 400px;
            height: 250px;
            margin-top: -2%;
            margin-left: 10%;
        }
        .form-section button:hover {
            background-color: #45a049;
        }
        .allotments-section {
            overflow-y: auto;
            max-height: 500px;
        }
        .allotments {
            margin-top: 20px;
        }
        .allotment-item {
            background-color: #fff;
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            align-items: center;
        }
        .allotment-item img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 15px;
        }
        .allotment-item p {
            margin: 0;
            color: #333;
            font-size: 16px;
        }
        .search-bar input {
            width: 100%;
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            margin-bottom: 20px;
        }
        .decorative-section {
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
        }
        .decorative-section img, .decorative-section lottie-player {
            max-width: 100%;
            height: auto;
        }
        @media screen and (max-width: 768px) {
            .container {
                flex-direction: column;
                align-items: center;
            }
            .form-section, .allotments-section {
                width: 100%;
                margin: 10px 0;
            }
        }
    </style>
</head>
<body>



<div class="container">
    <!-- yeh form appointment ka -->
    <div class="form-section">
        <h2>Book an Appointment</h2>
        <form action="appointmentForm" id="appointmentForm" method="get">
            <label for="date">Appointment Date:</label>
            <input type="date" id="date" name="date"pattern="\d{4}-\d{2}-\d{2}" required>

            <label for="patient">Patient ID:</label>
            <input type="text" id="patient" name="patient" placeholder="Enter Patient ID" required>

            <label for="doctorList">Doctor's ID:</label>
            <input type="text" id="doctorList" name="doctorList" placeholder="Enter Doctor's ID" required>

            <button type="submit">Book Appointment</button>
        </form>
        <img src="appointment.avif" alt="">
    </div>

    <!-- Allotments Section -->
    <div class="allotments-section">
        <div class="search-bar">
            <input type="text" id="searchPatient" placeholder="Search by Patient ID">
        </div>
        <h3>Allotted Appointments</h3>
        <div class="allotments" id="allotmentsList">
            <!-- Appointments sari ki sari idhr dikegi-->
        </div>
    </div>
</div>



<script>
    const form = document.getElementById('appointmentForm');
    const allotmentsList = document.getElementById('allotmentsList');
    const searchInput = document.getElementById('searchPatient');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const date = document.getElementById('date').value;
        const patientId = document.getElementById('patient-id').value;
        const doctorName = document.getElementById('doctor-name').value;


        const allotmentItem = document.createElement('div');
        allotmentItem.classList.add('allotment-item');
        allotmentItem.setAttribute('data-patient-id', patientId); // Set data attribute for search


        allotmentItem.innerHTML = `

            <div>
                <p><strong>Date:</strong> ${date}</p>
                <p><strong>Patient ID:</strong> ${patientId}</p>
                <p><strong>Doctor:</strong> ${doctorName}</p>
            </div>
        `;

        // Add the new appointment to the list
        allotmentsList.appendChild(allotmentItem);

        // Clear the form
        form.reset();
    });

    searchInput.addEventListener('input', function() {
        const searchValue = searchInput.value.toLowerCase();
        const allotments = document.querySelectorAll('.allotment-item');

        allotments.forEach(function(allotment) {
            const patientId = allotment.getAttribute('data-patient-id').toLowerCase();

            if (patientId.includes(searchValue)) {
                allotment.style.display = 'flex'; // Show matching item
            } else {
                allotment.style.display = 'none'; // Hide non-matching item
            }
        });
    });
</script>

</body>
</html>
