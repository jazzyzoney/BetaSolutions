package org.example.betasolutions.login;
import  org.example.betasolutions.employee.Employee;

public class Login {


    private int loginID;
    private String password;
    private String email;
    private Employee employee;

    public Login(String password, String email, Employee employee) {
        this.password = password;
        this.email = email;
        this.employee = employee;
    }

    public Login(){
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
