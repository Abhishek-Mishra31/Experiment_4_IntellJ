package com.abhishek.kumar.employee_management_system.model;

public class EmployeeDTO {
    private final int id;
    private final String name;
    private final String department;
    private final double salary;
    private final String phone;
    private final String address;

    public EmployeeDTO(int id, String name, String department, double salary, String phone, String address) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

}
