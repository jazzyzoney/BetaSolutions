package org.example.betasolutions.login;
import org.example.betasolutions.login.Login;
import org.example.betasolutions.ConnectionManager;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;


@Repository
public class LoginRepository {

    private final Connection conn;

    public LoginRepository(ConnectionManager connectionManager){
        this.conn = connectionManager.getConnection();
    }

    // create login
    public void createLogin(Login login){
        String SQLInsertLogin = "insert into login (email, password, employee_id) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLInsertLogin);
            preparedStatement.setString(1, login.getEmail());
            preparedStatement.setString(2, login.getPassword());
            preparedStatement.setInt(3, login.getEmployee().getEmployeeID());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
