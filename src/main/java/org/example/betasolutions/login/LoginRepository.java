package org.example.betasolutions.login;
import org.example.betasolutions.ConnectionManager;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



@Repository
public class LoginRepository {

    private final Connection conn;

    public LoginRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    // create login
    public void createLogin(Login login) {
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
    public boolean vaifyLogin(Login login) {
        String SQLSelectLogin = "SELECT * FROM profile WHERE email = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLSelectLogin);
            preparedStatement.setString(1, login.getEmail());
            preparedStatement.setString(2, login.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
