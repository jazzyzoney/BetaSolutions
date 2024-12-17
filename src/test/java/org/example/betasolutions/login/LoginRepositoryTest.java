package org.example.betasolutions.login;

import org.example.betasolutions.ConnectionManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")
class LoginRepositoryTest {


    @Autowired
    LoginRepository loginRepository;

    @Autowired
    private ConnectionManager connectionManager;

    Connection conn;

    @BeforeEach
    void setup(){
        conn = connectionManager.getConnection();
    }


    @AfterEach
    void tearDown(){
        try {
            conn.rollback();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void createLogin() {
        assertTrue(true);

    }

    @Test
    void verifyLogin() {
        Login Login = new Login("password1", "employee1@example.com", 1);
        boolean expected = loginRepository.verifyLogin(Login);
        assertTrue(expected);
    }
}