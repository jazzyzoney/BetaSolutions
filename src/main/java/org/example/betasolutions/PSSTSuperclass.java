package org.example.betasolutions;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PSSTSuperclass {
    protected ConnectionManager connectionManager;
    public Connection conn;

    public PSSTSuperclass(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.conn = connectionManager.getConnection();
    }

    public PreparedStatement insertAssignmentIntoTable(ModelInterface assignment,String sql){
        //String sql = "INSERT INTO " + tableName + " VALUES " + sqlValues;


        PreparedStatement preparedStatement;
        try{
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, assignment.getName());
            preparedStatement.setInt(2, assignment.getHours());
            preparedStatement.setInt(3, assignment.getDays());
            preparedStatement.setDouble(4, assignment.getTotalPrice());
            preparedStatement.setDate(5, assignment.getDeadline());
            preparedStatement.setDate(6, assignment.getStartDate());

            return preparedStatement;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }


    //read method
    // this one is for reading all tasks from a project with a specific projectID
    // we need to make a way to make it check that the inputted table name is correct or we might have sql injection
    public List<ModelInterface> readAllTasks(String tableName, int projectID, String tablePrefix, FactoryInterface factory) {
        List<ModelInterface> allObjects = new ArrayList<>();
        String sql = "select * from " + tableName + " where projectID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(tablePrefix + "ID");
                String name = resultSet.getString(tablePrefix + "Name");
                int hours = resultSet.getInt(tablePrefix + "TotalHours");
                int days = resultSet.getInt(tablePrefix + "TotalDays");
                double totalPrice = resultSet.getInt(tablePrefix + "TotalPrice");
                Date endDate = resultSet.getDate(tablePrefix + "DeadLine");
                Date startDate = resultSet.getDate(tablePrefix + "StartDate");
                allObjects.add(factory.build(id,name, hours, days, totalPrice, endDate, startDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allObjects;
    }


    // Read Method
    // this one is for reading all tasks from a project with a specific projectID and employeeID so see all tasks for a specific employee
    public List<ModelInterface> readAllTasksForEmployee(String tableName, int EmployeeID,int projectID, String tablePrefix,FactoryInterface factory) {
        List<ModelInterface> allObjects = new ArrayList<>();
        String sql =  "SELECT * FROM " + tableName + " WHERE employeeID = ? AND projectID = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, EmployeeID);
            preparedStatement.setInt(2, projectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(tablePrefix + "ID");
                String name = resultSet.getString(tablePrefix + "Name");
                int hours = resultSet.getInt(tablePrefix + "TotalHours");
                int days = resultSet.getInt(tablePrefix + "TotalDays");
                double totalPrice = resultSet.getInt(tablePrefix + "TotalPrice");
                Date endDate = resultSet.getDate(tablePrefix + "DeadLine");
                Date startDate = resultSet.getDate(tablePrefix + "StartDate");
                allObjects.add(factory.build(id,name, hours, days, totalPrice, endDate, startDate));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allObjects;
    }
    public boolean deleteObjectFromTable(String tableName, String tablePrefix,int functionID) {
        String sql = "DELETE FROM " + tableName + " WHERE " + tablePrefix + "ID = ? ";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, functionID);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateObjectString(String tableName, String attributeName, int functionID, String newValue) {
        String sql = "UPDATE " + tableName + " SET " + attributeName + " = ? WHERE " + tableName + "ID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, functionID);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // so for this method to work on subProject you need to input subProject
    public int getTableInt(String tableName, String intColumnName) {
        String sql = "SELECT " + intColumnName + " FROM " + tableName + " WHERE " + intColumnName + " = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, intColumnName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(intColumnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public String getTableString(String tableName, String stringColumnName) {
        String sql = "SELECT " + stringColumnName + " FROM " + tableName + " WHERE " + stringColumnName + " = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, stringColumnName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(stringColumnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public int getTableIntByString(String tableName, String intColumnName, String columnName2, String value) {
        String sql = "SELECT " + intColumnName + " FROM " + tableName + " WHERE " + columnName2 + " = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(intColumnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getTableIntByInt(String tableName, String intColumnName, String columnName2, int value) {
        String sql = "SELECT " + intColumnName + " FROM " + tableName + " WHERE " + columnName2 + " = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(intColumnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean deleteAllWhere(String tableName, String whereStatement){

            String sql = "DELETE FROM " + tableName + " WHERE " + whereStatement;

            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
    }

}
