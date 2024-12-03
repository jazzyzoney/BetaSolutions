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
    //create method for inserting into table with a prepared statement and returning the generated keys for the object.
    //it intakes a ModelInterface object and a sql string like "INSERT INTO PROJECT VALUES (?,?,?,?,?,?,?)" for example.
    // if the table has more columns you need to add more ? to the sql string and set them in the prepared statement in the child class.
    // see projectRepository for example.
    // VERY IMPORTANT! you need to call preparedStatement.executeUpdate(); in the child in order for it to work.
    public PreparedStatement insertAssignmentIntoTable(ModelInterface assignment,String sql){
        PreparedStatement preparedStatement;
        try{
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, assignment.getName());
            preparedStatement.setInt(2, assignment.getHours());
            preparedStatement.setInt(3, assignment.getDays());
            preparedStatement.setDouble(4, assignment.getTotalPrice());
            preparedStatement.setDate(5, assignment.getDeadline());
            preparedStatement.setDate(6, assignment.getStartDate());
            //this returns a basic version of our models with only the id, name, hours, days, totalprice, deadline and startdate.
            // it can do this because the models all implement the ModelInterface.

            return preparedStatement;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }


    //read method
    // this one is for reading all tasks from a project with a specific projectID
    // so it would look like this: readAllAssignments("task", 1, "task", Task::new); for example.
    // it will return a list of all tasks in the task table with the projectID 1.
    // tablePrefix is the prefix of the table you are reading from. so if you are reading from the task table it would be "task".
    // factory is a lambda function that takes in the parameters for the model you are reading from the table.
    //lambda is a function that takes in parameters and returns a new object of the type you are reading from the table.
    //we need to use this because we are reading from a table and we need to create new objects of the type we are reading from the table.
    public List<ModelInterface> readAllAssignments(String tableName, String tablePrefix, FactoryInterface factory){//}, String sqlWhereClause){//}, int projectID) {
        List<ModelInterface> allObjects = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName ;//+ " WHERE " + sqlWhereClause;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //preparedStatement.setInt(1, projectID);
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

    
    public List<ModelInterface> readAllAssignmentsBelongingToProject(String tableName, String tablePrefix, FactoryInterface factory, int projectID) {
        List<ModelInterface> allObjects = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE  projectID = ?" ;

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
                allObjects.add(factory.build(id, name, hours, days, totalPrice, endDate, startDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allObjects;
    }

    // Read Method
    
    public List<ModelInterface> readAllAssignmentsForEmployee(String joinTableName, int employeeID, int projectID,
            String tableName, FactoryInterface factory) {

        List<ModelInterface> allObjects = new ArrayList<>(); //returned at end of method.

        
        String sql =   "SELECT " +
                tableName + "." + tableName + "ID, " + //eg. task.taskID,
                tableName + "." + tableName + "Name, " +//task.taskName.
                tableName + "." + tableName + "TotalHours, " + //etc.
                tableName + "." + tableName + "TotalDays, " +
                tableName + "." + tableName + "TotalPrice, " +
                tableName + "." + tableName + "Deadline, " +
                tableName + "." + tableName + "StartDate " +
               // joinTableName + ".employeeID" +  //select employeeID from joinTable
                joinTableName + "." + tableName + "ID" + //select assignmentID from joinTable
                " JOIN " + tableName + " ON " + tableName + "." + tableName + "ID, " + joinTableName + "." + tableName + "ID" +
                "WHERE " + joinTableName + ".employeeID = ?";
                //JOIN joinTable ON assignment.assignementID, joinTable.assignmentID


        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, employeeID); //WHERE assignment.projectID = 1 ....

            ResultSet resultSet = preparedStatement.executeQuery(); //connect.

            //for each assignmentObject, get variables.
            while (resultSet.next()) {
                int id = resultSet.getInt(tableName + "ID");
                String name = resultSet.getString(tableName + "Name");
                int hours = resultSet.getInt(tableName + "TotalHours");
                int days = resultSet.getInt(tableName + "TotalDays");
                double totalPrice = resultSet.getDouble(tableName + "TotalPrice");
                Date endDate = resultSet.getDate(tableName + "Deadline");
                Date startDate = resultSet.getDate(tableName + "StartDate");

                //add new assignmentobject to allObjects list.
                allObjects.add(factory.build(id, name, hours, days, totalPrice, endDate, startDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allObjects; //return list containing assignementObjects belonging to employee.
    }

    //delete method
    //this one is for deleting an object from a table with a specific ID.
    //so it would look like this: deleteObjectFromTable("task", "task", 1); for example.
    //it will delete the task with the taskID 1 from the task table.
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
    //update method
    //this one is for updating a string value in a table with a specific ID.
    //so it would look like this: updateObjectString("task", "taskName", 1, "Task 2"); for example.
    //it will update the taskName of the task with the taskID 1 to "Task 2".
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

    //read method
    //this one is for getting a string value from a table with a specific ID or int value.
    //so it would look like this: getTableStringByInt("project", "projectName", "projectID", 1); for example.
    //it will return the projectName of the project with the projectID 1.
    public String getTableStringByInt(String tableName, String selectedColumn, String whereColumn, int inputValue ) {
        String sql = "SELECT " + selectedColumn + " FROM " + tableName + " WHERE " + whereColumn + " = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, inputValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(selectedColumn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // read method
    // this one is for getting a string value from a table with a specific where clause.
    // so it would look like this: getTableStringByString("project", "projectOwner", "projectName", "Project A"); for example.
    // it will return the projectOwner of the project with the projectName "Project A".
    public String getTableStringByString(String tableName, String selectedColumn, String whereColumn, String inputValue ) {
        String sql = "SELECT " + selectedColumn + " FROM " + tableName + " WHERE " + whereColumn + " = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, inputValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(selectedColumn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //read method
    //this one is for getting an string value from a table with a specific int value.
    //so it would look like this: getTableIntByString("task", "taskID", "taskName", "Task 1"); for example.
    //it will return the taskID of the task with the taskName "Task 1".
    //personal note dont we have this one allready ?
    public int getTableIntByString(String tableName, String selectedColumn, String whereColumn, String value) {
        String sql = "SELECT " + selectedColumn + " FROM " + tableName + " WHERE " + whereColumn + " = ?";
        //eg. SELECT taskID FROM task WHERE taskName = "Task 1"

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql); //instantiate preparedStatement.
            preparedStatement.setString(1, value); //set String value. eg. taskName = "Task 1"
            ResultSet resultSet = preparedStatement.executeQuery();//commit
            if (resultSet.next()) {
                return resultSet.getInt(selectedColumn);//get first int where where-clause passed. eg. taskName = "Task 1"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; //return -1 if method failed
    }

    //read method
    //this one is for getting an int value from a table with a specific int value.
    //so it would look like this: getTableIntByInt("task", "taskID", "taskTotalHours", 100); for example.
    //it will return the taskID of the task with the taskTotalHours 100.
    public int getTableIntByInt(String tableName, String selectedColumn, String whereColumn, int value) {
        String sql = "SELECT " + selectedColumn + " FROM " + tableName + " WHERE " + whereColumn + " = ?";
        //eg. SELECT taskID FROM task WHERE taskTotalHours = 100

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, value); //set whereColumn = value. eg: taskTotalHours = 100
            ResultSet resultSet = preparedStatement.executeQuery(); //commit

            if (resultSet.next()) {
                return resultSet.getInt(selectedColumn); //returns first int in resultset (eg. first id where taskTotalHours = 100)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;//return -1 if method fails.
    }
    // you will never guess what this one does.
    // it deletes all rows in a table where a specific where statement is true.
    // so it would look like this: deleteAllWhere("task", "taskTotalHours = 100"); for example.
    // it will delete all tasks where the taskTotalHours is 100.
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
