package org.example.betasolutions;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSSTSuperclass {
    protected final Connection conn;

    public PSSTSuperclass(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }
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
    //read method
    // this one is for reading all tasks from a project with a specific projectID
    public List<ModelInterface> readAllTasks(String tableName, int projectID, String tablePrefix, FactoryInterface factory) {
        List<ModelInterface> allObjects = new ArrayList<>();
        String sql = "select * from " + tableName + " where project_id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(tablePrefix + "ID");
                String name = resultSet.getString(tablePrefix + "Name");
                int hours = resultSet.getInt("hours");
                int days = resultSet.getInt("days");
                double totalPrice = resultSet.getInt("total_price");
                Date endDate = resultSet.getDate("end_date");
                Date startDate = resultSet.getDate("start_date");
                allObjects.add(factory.create(id,name, hours, days, totalPrice, endDate, startDate));
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
        String sql = "select * from " + tableName + " where employee_id = ? and where project_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(2, EmployeeID);
            preparedStatement.setInt(3, projectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(tablePrefix + "ID");
                String name = resultSet.getString(tablePrefix + "Name");
                int hours = resultSet.getInt("hours");
                int days = resultSet.getInt("days");
                double totalPrice = resultSet.getInt("total_price");
                Date endDate = resultSet.getDate("end_date");
                Date startDate = resultSet.getDate("start_date");
                allObjects.add(factory.create(id,name, hours, days, totalPrice, endDate, startDate));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allObjects;
    }

    /*
    public List<String> read(String tableName, String idName, int id, Object model){
        String sql = "SELECT * FROM " + tableName + " WHERE " + idName + " =?";
*/

    /*
    read
    insert
    delete
    update
     */


}
