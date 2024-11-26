package org.example.betasolutions;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PSSTSuperclass {

    //create method
    public int create(Object object, String tableName,String name) {
        String sql = "insert into " + tableName + " (" + name + ") values(?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, Calculator.calculateHours());
            preparedStatement.setString(3, Calculator.calculatedays());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
        return 0;
    }

}
