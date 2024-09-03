<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bed Management - General Ward</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        .bed-icons {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(60px, 1fr));
            gap: 15px;
            margin-top: 20px;
        }

        .bed-container {
            background-color: #A1CCD1; /* Blue for available beds */
            padding: 15px;
            border-radius: 8px;
            display: flex;
            justify-content: center;
            align-items: center;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .bed-container.occupied {
            background-color: #FFCACC; /* Red for occupied beds */
        }

        .bed-container:hover {
            background-color: #b2b0b0;
        }

        .popup {
            display: none;
            position: fixed;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .popup.active {
            display: block;
        }

        .popup input {
            margin-bottom: 10px;
            padding: 8px;
            width: 100%;
        }

        .popup button {
            padding: 8px 16px;
            background-color: #51cf66;
            border: none;
            color: white;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
        }

        .popup button.remove {
            background-color: #ff6b6b;
        }
    </style>
</head>
<body>
    <div class="container">
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
    </div>

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
