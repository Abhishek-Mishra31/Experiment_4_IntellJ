package com.abhishek.kumar.employee_management_system.model;

import javax.persistence.*;

@Entity
@Table(name = "employeedetails")
public class EmployeeDetails {

    @Id
    private int id;
    private String address;
    private String phone;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.id = employee.getId();
    }

    public EmployeeDetails() {}
    public EmployeeDetails(String Address, String phone) {
        this.address = Address;
        this.phone = phone;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPhone() { return phone; }
    public void setPhone(String Phone) { this.phone = Phone; }
    public String getAddress() { return address; }
    public void setAddress(String Address) { this.address = Address; }

}
