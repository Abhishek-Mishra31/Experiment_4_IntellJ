package com.abhishek.kumar.employee_management_system.service;

import com.abhishek.kumar.employee_management_system.model.Employee;
import com.abhishek.kumar.employee_management_system.model.EmployeeDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.abhishek.kumar.employee_management_system.model.EmployeeDetails;

import java.util.List;

public class EmployeeService {
    private static final SessionFactory factory;

    static {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
    }

    public static void saveEmployee(Employee employee , EmployeeDetails employeeDetails) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        employee.setEmployeeDetails(employeeDetails);
        session.persist(employee);
        transaction.commit();
    }

    public static List<Employee> getAllEmployees() {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();
        transaction.commit();
        return employees;
    }

    public static List<EmployeeDTO> getAllEmployeeDAO() {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<EmployeeDTO> employees = session.createQuery(
                "SELECT new com.abhishek.kumar.employee_management_system.model.EmployeeDTO (e.id , e.name , e.department , e.salary , d.phone , d.address) " +
                        "from Employee e join EmployeeDetails d ON e.id = d.id", EmployeeDTO.class).getResultList();

        transaction.commit();
        return employees;
    }


    public static void deleteEmployee(int employeeId) {
        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, employeeId);
        session.remove(employee);
        transaction.commit();
    }

}
