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

    //create method
    public int insertObjectIntoTable(ModelInterface object, String tableName, String projectName,int hours, int days, double totalPrice) {
        String sql = "insert into " + tableName + " values(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, projectName);
            preparedStatement.setInt(2, hours);
            preparedStatement.setInt(3, days);
            preparedStatement.setDouble(4, totalPrice);
            preparedStatement.setDate(5, object.getDeadline());
            preparedStatement.setDate(6, object.getStartDate());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);//return objectID.
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
        return 0;//if failed
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
    public boolean deleteObjectFromTable(String tableName, String tablePrefix,int projectID, int objectID) {
        String sql = "DELETE FROM " + tableName + " WHERE " + tablePrefix + "ID"+" = ? AND projectID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, objectID);
            preparedStatement.setInt(2, projectID);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void updateObjectAtrribute(String tableName, String name, int functionID, String newName) {
        String sql = "UPDATE " + tableName + " SET " + name + " = ? WHERE " + "ID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, functionID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTableInt(String tableName, String intName){
        return 0;
    }
}
