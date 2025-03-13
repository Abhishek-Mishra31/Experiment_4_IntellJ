<%--
  Created by IntelliJ IDEA.
  User: Abhishek kumar
  Date: 06-03-2025
  Time: 09:55 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        .container {
            background: white;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            width: 350px;
            text-align: center;
        }

        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 10px;
            background: blue;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <h4><u>Add Employee</u></h4>
    <form id="addEmployee" onsubmit="enroll(event)">
        <input type="text" name="action" value="addEmployee" hidden>
        <input type="text" name="name" placeholder="Enter Employee Name" required>
        <input type="text" name="department" placeholder="Enter Employee department" required>
        <input type="text" name="salary" placeholder="Enter Employee salary" required>
        <input type="text" name="phone" placeholder="Enter Employee phone" required>
        <input type="text" name="address" placeholder="Enter Employee Address" required>

        <button type="submit">Add employee</button>
    </form>

    <form id="getdetails" onsubmit="getDetails(event)">
        <input type="text" name="action" value="details" hidden />
        <button type="submit">Get employee details</button>
    </form>

</div>

<script>
    function enroll(event) {
        event.preventDefault();
        let form = new FormData(document.getElementById('addEmployee'));
        fetch("hello-servlet", {
            method: "POST",
            body: new URLSearchParams(form),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then((res) => {
            if (res.redirected) {
                window.location.href = res.url;
                return Promise.reject("Redirected")
            }
            return res.text()
        }).then((data) => {
            alert(data)
        })
    }

    function getDetails(event) {
        event.preventDefault();
        const data = new FormData(document.getElementById('getdetails'));
        fetch("hello-servlet", {
            method: "POST",
            body: new URLSearchParams(data),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then((res) => {
            if (res.redirected) {
                window.location.href = res.url;
                return Promise.reject("Redirected")
            }
            return res.text()
        }).then((data) => {
            alert(data);
        })
    }
</script>
</body>
</html>
