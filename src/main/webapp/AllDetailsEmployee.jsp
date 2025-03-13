<%--
  Created by IntelliJ IDEA.
  User: Abhishek
  Date: 12-02-2025
  Time: 09:40 pm
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            text-align: center;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            border: 1px solid black;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        .details-btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #2ecc71;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: 0.3s;
        }

        .details-btn:hover {
            background-color: #27ae60;
        }

        #deleteEmployeeForm {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin: 20px auto;
        }

        #deleteEmployeeForm input {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        #deleteEmployeeForm button {
            background: #e74c3c;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
            transition: background 0.3s;
        }

        #deleteEmployeeForm button:hover {
            background: #c0392b;
        }
    </style>
</head>
<body>
<h2>Employee List</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Department</th>
        <th>Salary</th>
        <th>Phone</th>
        <th>Address</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.department}</td>
            <td>${employee.salary}</td>
            <td>${employee.phone}</td>
            <td>${employee.address}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form id="deleteEmployeeForm" onsubmit="deleteEmployee(event)">
    <input type="text" name="action" value="deleteEmployee" hidden>
    <input type="number" name="employeeId" placeholder="Enter Employee ID to delete" required>
    <button type="submit">Delete</button>
</form>

<a href="listEmployees.jsp" class="details-btn">Go Back</a>

<script>
    function deleteEmployee(event) {
        event.preventDefault();
        var form = new FormData(document.getElementById('deleteEmployeeForm'));
        fetch("hello-servlet", {
            method: 'POST',
            body: new URLSearchParams(form),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then((res) => {
            if (res.redirected) {
                window.location.href = res.url;
                return Promise.reject("Redirected");
            }
            return res.text();
        }).then((data) => {
            alert(data);
        }).catch(err => console.log(err));
    }
</script>
</body>
</html>
