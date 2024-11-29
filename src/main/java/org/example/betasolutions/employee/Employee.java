package org.example.betasolutions.employee;

public class Employee {
    int employeeID;
    String employeeName;
    String employeeOffice;
    String employeeProficiency;
    String employeeSalary;

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

}
