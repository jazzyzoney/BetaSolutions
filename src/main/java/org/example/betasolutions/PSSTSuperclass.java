package org.example.betasolutions;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PSSTSuperclass {

    //create method
    public int create(ModelInterface object, String tableName,String name,int hours, int days, int totalPrice) {
        String sql = "insert into " + tableName + " (" + name + ") values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, hours);
            preparedStatement.setInt(3, days);
            preparedStatement.setInt(4, totalPrice);
            preparedStatement.setDate(5, object.getEndDate());
            preparedStatement.setDate(6, object.getStartDate());
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
