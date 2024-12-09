package org.example.betasolutions.employee;

public class Employee {
    private int employeeID;
    private String employeeName;
    private String employeeOffice;
    private String employeeProficiency;
    private String employeeSalary;

    //getters
    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeOffice() {
        return employeeOffice;
    }

    public String getEmployeeProficiency() {
        return employeeProficiency;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }

    //setters
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeOffice(String employeeOffice) {
        this.employeeOffice = employeeOffice;
    }

    public void setEmployeeProficiency(String employeeProficiency) {
        this.employeeProficiency = employeeProficiency;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    //constructor when needed
    public Employee(int employeeID, String employeeName, String employeeOffice, String employeeProficiency, String employeeSalary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeOffice = employeeOffice;
        this.employeeProficiency = employeeProficiency;
        this.employeeSalary = employeeSalary;
    }

    //empty constructor
    public Employee() {
    }
}
