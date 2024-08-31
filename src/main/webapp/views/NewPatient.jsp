<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Admission Form</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="form-container">
        <u><b><h2>Patient Registration Form</h2></b></u>
        <form action="patients" method="post">
            <div class="form-group">
                <label for="aadharNumber">Aadhaar Number:</label>
                <input type="text" id="aadharNumber" name="aadharNumber" required>
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
</body>
</html>
