
-- -----------------------------------------------------
-- Schema BetaSolutionsDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BetaSolutionsDB
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `BetaSolutionsDB`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS project (
                                       projectID INT AUTO_INCREMENT PRIMARY KEY,
                                       projectName VARCHAR(100) NOT NULL,
                                       projectOwner VARCHAR(100) NOT NULL,
                                       projectTotalHours INT NOT NULL,
                                       projectTotalDays INT NOT NULL,
                                       projectTotalPrice DOUBLE NOT NULL,
                                       projectDeadline DATE NOT NULL,
                                       projectStartDate DATE NOT NULL
);

-- -----------------------------------------------------
-- Table `BetaSolutionsDB`.`subProject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS subProject (
                                          subProjectID INT AUTO_INCREMENT PRIMARY KEY,
                                          subProjectName VARCHAR(100) NOT NULL,
                                          subProjectTotalHours INT NOT NULL,
                                          subProjectTotalDays INT NOT NULL,
                                          subProjectTotalPrice DOUBLE NOT NULL,
                                          subProjectDeadline DATE NOT NULL,
                                          subProjectStartDate DATE NOT NULL,
                                          projectID INT NOT NULL,
                                          active TINYINT NOT NULL,
                                          FOREIGN KEY (projectID) REFERENCES project (projectID)
);

-- -----------------------------------------------------
-- Table `BetaSolutionsDB`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS task (
                                    taskID INT AUTO_INCREMENT PRIMARY KEY,
                                    taskName VARCHAR(100) NOT NULL,
                                    taskTotalHours INT NOT NULL,
                                    taskTotalDays INT NOT NULL,
                                    taskTotalPrice DOUBLE NOT NULL,
                                    taskDeadline DATE NOT NULL,
                                    taskStartDate DATE NOT NULL,
                                    projectID INT NULL,
                                    subProjectID INT NULL,
                                    active TINYINT NOT NULL,
                                    FOREIGN KEY (projectID) REFERENCES project (projectID),
                                    FOREIGN KEY (subProjectID) REFERENCES subProject (subProjectID)
);

-- -----------------------------------------------------
-- Table `BetaSolutionsDB`.`subTask`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS subTask (
                                       subTaskID INT AUTO_INCREMENT PRIMARY KEY,
                                       subTaskName VARCHAR(100) NOT NULL,
                                       subTaskTotalHours INT NOT NULL,
                                       subTaskTotalDays INT NOT NULL,
                                       subTaskTotalPrice DOUBLE NOT NULL,
                                       subTaskDeadline DATE NOT NULL,
                                       subTaskStartDate DATE NOT NULL,
                                       taskID INT NOT NULL,
                                       active TINYINT NOT NULL,
                                       FOREIGN KEY (taskID) REFERENCES task (taskID)
);

-- -----------------------------------------------------
-- Table `BetaSolutionsDB`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS employee (
                                        employeeID INT AUTO_INCREMENT PRIMARY KEY,
                                        employeeName VARCHAR(100) NOT NULL,
                                        employeeOffice VARCHAR(100) NOT NULL,
                                        employeeProficiency VARCHAR(100) NOT NULL,
                                        employeeSalary DOUBLE NOT NULL
);

-- -----------------------------------------------------
-- Table `BetaSolutionsDB`.`employeeSubTask`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS employeeSubTask (
                                               subTaskID INT NOT NULL,
                                               employeeID INT NOT NULL,
                                               projectID INT NOT NULL,
                                               FOREIGN KEY (subTaskID) REFERENCES subTask (subTaskID),
                                               FOREIGN KEY (projectID) REFERENCES project (projectID),
                                               FOREIGN KEY (employeeID) REFERENCES employee (employeeID)
);

-- -----------------------------------------------------
-- Table `BetaSolutionsDB`.`employeeTask`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS employeeTask (
                                            taskID INT NOT NULL,
                                            employeeID INT NOT NULL,
                                            projectID INT NOT NULL,
                                            FOREIGN KEY (taskID) REFERENCES task (taskID),
                                            FOREIGN KEY (projectID) REFERENCES project (projectID),
                                            FOREIGN KEY (employeeID) REFERENCES employee (employeeID)
);

-- -----------------------------------------------------
-- Table `BetaSolutionsDB`.`employeeSubProject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS employeeSubProject (
                                                  subProjectID INT NOT NULL,
                                                  employeeID INT NOT NULL,
                                                  projectID INT NOT NULL,
                                                  FOREIGN KEY (subProjectID) REFERENCES subProject (subProjectID),
                                                  FOREIGN KEY (projectID) REFERENCES project (projectID),
                                                  FOREIGN KEY (employeeID) REFERENCES employee (employeeID)
);

-- -----------------------------------------------------
-- Table `BetaSolutionsDB`.`profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS profile (
                                       loginID INT AUTO_INCREMENT PRIMARY KEY,
                                       password VARCHAR(50) NOT NULL,
                                       email VARCHAR(50) NOT NULL,
                                       employeeID INT NOT NULL,
                                       FOREIGN KEY (employeeID) REFERENCES employee (employeeID)
);

-- Add test data

-- Insert sample data into project table
INSERT INTO project (projectName, projectOwner, projectTotalHours, projectTotalDays, projectTotalPrice, projectDeadline, projectStartDate)
VALUES
    ('Project A', 'Owner 1', 500, 30, 10000.0, '2024-12-31', '2024-11-01'),
    ('Project B', 'Owner 2', 300, 20, 8000.0, '2025-01-15', '2024-12-01');

-- Insert sample data into subProject table
INSERT INTO subProject (subProjectName, subProjectTotalHours, subProjectTotalDays, subProjectTotalPrice, subProjectDeadline, subProjectStartDate, projectID, active)
VALUES
    ('SubProject 1', 200, 15, 5000.0, '2024-12-15', '2024-11-05', 1, 1),
    ('SubProject 2', 100, 10, 3000.0, '2025-01-05', '2024-12-10', 2, 1);

-- Insert sample data into task table
INSERT INTO task (taskName, taskTotalHours, taskTotalDays, taskTotalPrice, taskDeadline, taskStartDate, projectID, subProjectID, active)
VALUES
    ('Task 1', 100, 5, 1500.0, '2024-12-05', '2024-11-10', 1, 1, 1),
    ('Task 2', 50, 4, 1200.0, '2024-12-20', '2024-11-15', 2, 2, 1);

-- Insert sample data into employee table
INSERT INTO employee (employeeName, employeeOffice, employeeProficiency, employeeSalary)
VALUES
    ('Employee 1', 'Office 1', 'Developer', 5000.0),
    ('Employee 2', 'Office 2', 'Designer', 4000.0);

-- Insert sample data into subTask table
INSERT INTO subTask (subTaskName, subTaskTotalHours, subTaskTotalDays, subTaskTotalPrice, subTaskDeadline, subTaskStartDate, taskID, active)
VALUES
    ('SubTask 1', 50, 3, 500.0, '2024-12-03', '2024-11-10', 1, 1),
    ('SubTask 2', 25, 2, 300.0, '2024-12-18', '2024-11-15', 2, 1);

-- Insert sample data into profile table
INSERT INTO profile (password, email, employeeID)
VALUES
    ('password1', 'employee1@example.com', 1),
    ('password2', 'employee2@example.com', 2);

-- -----------------------------------------------------
-- Finished Creating Schema and Adding Test Data
-- -----------------------------------------------------