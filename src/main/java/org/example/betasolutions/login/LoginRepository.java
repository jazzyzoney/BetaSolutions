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

    // create login
    public void createLogin(Login login) {
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


    public int findEmployeeByEmail(String email) {
        String SQLFindEmployeeByEmail = "SELECT * FROM employee WHERE email = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLFindEmployeeByEmail);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int employeeID= resultSet.getInt("employee_id");

                // Set other fields as needed
                return employeeID;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
