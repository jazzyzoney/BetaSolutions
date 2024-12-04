package org.example.betasolutions.login;

import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class LoginRepository {

    private final Connection conn;

    public LoginRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();

    }
    public int saveUser(Login login) {
        String sql = "INSERT INTO login (password, email) VALUES (?, ?)";
try(PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
prepraredStatement.setString(1, login.getPassword());
preparedStatement.setString(2, login.getEmail());
preparedStatement.executeUpdate();
ResultSet resultSet = preparedStatement.getGeneratedKeys();
if(resultSet.next()) {

}


    }


}
