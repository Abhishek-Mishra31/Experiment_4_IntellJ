package com.abhishek.kumar.employee_management_system;

import com.abhishek.kumar.employee_management_system.model.EmployeeDTO;
import com.abhishek.kumar.employee_management_system.model.EmployeeDetails;
import com.abhishek.kumar.employee_management_system.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.abhishek.kumar.employee_management_system.model.Employee;
import com.abhishek.kumar.employee_management_system.model.User;
import com.abhishek.kumar.employee_management_system.service.EmployeeService;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "employee data", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {
        System.out.println("Hello Servlet");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       List<EmployeeDTO> employees = EmployeeService.getAllEmployeeDAO();
       request.setAttribute("employees", employees);
       request.getRequestDispatcher("AllDetailsEmployee.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        System.out.println("action: " + action);

        if (action.equals("details")) {
            List<Employee> employees = EmployeeService.getAllEmployees();
            HttpSession session = req.getSession();
            session.setAttribute("employees", employees);
            resp.sendRedirect("listEmployees.jsp");
        }

        if (action.equals("register")) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");

            User admin = new User(name, email);
            UserService.saveUsers(admin);
            resp.sendRedirect("Homepage.jsp");
        }

        if (action.equals("addEmployee")) {
            String name = req.getParameter("name");
            String department = req.getParameter("department");
            String salary = req.getParameter("salary");
            String phone = req.getParameter("phone");
            String address = req.getParameter("address");

            Employee employee = new Employee();
            employee.setName(name);
            employee.setDepartment(department);
            employee.setSalary(Double.parseDouble(salary));

            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setPhone(phone);
            employeeDetails.setAddress(address);
            EmployeeService.saveEmployee(employee, employeeDetails);
            resp.getWriter().println("Employee added successfully");
        }

        if(action.equals("deleteEmployee")) {
            int id = Integer.parseInt(req.getParameter("employeeId"));
            EmployeeService.deleteEmployee(id);
            resp.getWriter().println("Employee deleted successfully");
        }
    }


    public void destroy() {

    }
}