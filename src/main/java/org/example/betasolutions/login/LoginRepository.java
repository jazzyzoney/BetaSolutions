package org.example.betasolutions.login;
import org.example.betasolutions.ConnectionManager;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class LoginRepository {

    private final Connection conn;

    public LoginRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    /*// create login
    public void createLogin(Login login){
        String SQLInsertEmployee = "INSERT INTO employee (employee_id, employee_name, employee_office, employee_proficiency, employee_salary) VALUES (?,?,?,?,?)";
        String SQLInsertLogin = "INSERT INTO profile (email, password, employee_id) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLInsertLogin);
            preparedStatement.setString(1, login.getEmail());
            preparedStatement.setString(2, login.getPassword());
            preparedStatement.setInt(3, login.getEmployeeID());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Login findByEmail(String email) {
        String SQLFindByEmail = "SELECT * FROM profile WHERE email = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLFindByEmail);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Login login = new Login();
                login.setEmail(resultSet.getString("email"));
                login.setPassword(resultSet.getString("password"));
                // Set other fields as needed
                return login;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}*/

    // create login
    public void createLogin(Login login) {
        String SQLCheckEmployee = "SELECT COUNT(*) FROM employee WHERE employee_id = ?";
        String SQLInsertEmployee = "INSERT INTO employee (employee_id, employee_name, employee_office, employee_proficiency, employee_salary) VALUES (?,?,?,?,?)";
        String SQLInsertLogin = "INSERT INTO profile (email, password, employee_id) VALUES (?,?,?)";

        try {
            // Check if employee exists
            PreparedStatement checkStmt = conn.prepareStatement(SQLCheckEmployee);
            checkStmt.setInt(1, login.getEmployeeID());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            // If employee does not exist, insert it
            if (count == 0) {
                PreparedStatement insertEmployeeStmt = conn.prepareStatement(SQLInsertEmployee);
                insertEmployeeStmt.setInt(1, login.getEmployeeID());
                insertEmployeeStmt.setString(2, "Employee Name"); // Replace with actual employee name
                insertEmployeeStmt.setString(3, "Employee Office"); // Replace with actual employee office
                insertEmployeeStmt.setString(4, "Employee Proficiency"); // Replace with actual employee proficiency
                insertEmployeeStmt.setString(5, "Employee Salary"); // Replace with actual employee salary
                insertEmployeeStmt.executeUpdate();
            }

            // Insert login
            PreparedStatement insertLoginStmt = conn.prepareStatement(SQLInsertLogin);
            insertLoginStmt.setString(1, login.getEmail());
            insertLoginStmt.setString(2, login.getPassword());
            insertLoginStmt.setInt(3, login.getEmployeeID());
            insertLoginStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}