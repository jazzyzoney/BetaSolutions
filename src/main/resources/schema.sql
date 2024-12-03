-- H2 Database Schema: BetaSolutionsDB

-- Create schema (H2 automatically creates it with the first table creation)
-- CREATE SCHEMA IF NOT EXISTS BetaSolutionsDB;

-- Use schema (optional for H2)
-- SET SCHEMA BetaSolutionsDB;

-- Table: project
CREATE TABLE IF NOT EXISTS project (
                                       project_id INT PRIMARY KEY AUTO_INCREMENT,
                                       project_name VARCHAR(100) NOT NULL,
    project_owner VARCHAR(100) NOT NULL,
    project_total_hours INT NOT NULL,
    project_total_days INT NOT NULL,
    project_total_price DOUBLE NOT NULL,
    project_deadline DATE NOT NULL,
    project_start_date DATE NOT NULL,
    project_active BOOLEAN NOT NULL DEFAULT TRUE
    );

-- Table: sub_project
CREATE TABLE IF NOT EXISTS sub_project (
                                           sub_project_id INT PRIMARY KEY AUTO_INCREMENT,
                                           sub_project_name VARCHAR(100) NOT NULL,
    sub_project_total_hours INT NOT NULL,
    sub_project_total_days INT NOT NULL,
    sub_project_total_price DOUBLE NOT NULL,
    sub_project_deadline DATE NOT NULL,
    sub_project_start_date DATE NOT NULL,
    project_id INT NOT NULL,
    sub_project_active BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
    );

-- Table: task
CREATE TABLE IF NOT EXISTS task (
                                    task_id INT PRIMARY KEY AUTO_INCREMENT,
                                    task_name VARCHAR(100) NOT NULL,
    task_total_hours INT NOT NULL,
    task_total_days INT NOT NULL,
    task_total_price DOUBLE NOT NULL,
    task_deadline DATE NOT NULL,
    task_start_date DATE NOT NULL,
    sub_project_id INT,
    task_active BOOLEAN NOT NULL DEFAULT TRUE,
    project_id INT NOT NULL,
    FOREIGN KEY (sub_project_id) REFERENCES sub_project (sub_project_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
    );

-- Table: sub_task
CREATE TABLE IF NOT EXISTS sub_task (
                                        sub_task_id INT PRIMARY KEY AUTO_INCREMENT,
                                        sub_task_name VARCHAR(100) NOT NULL,
    sub_task_total_hours INT NOT NULL,
    sub_task_total_days INT NOT NULL,
    sub_task_total_price DOUBLE NOT NULL,
    sub_task_deadline DATE NOT NULL,
    sub_task_start_date DATE NOT NULL,
    task_id INT NOT NULL,
    sub_task_active BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (task_id) REFERENCES task (task_id) ON DELETE CASCADE
    );

-- Table: employee
CREATE TABLE IF NOT EXISTS employee (
                                        employee_id INT PRIMARY KEY AUTO_INCREMENT,
                                        employee_name VARCHAR(100) NOT NULL,
    employee_office VARCHAR(100) NOT NULL,
    employee_proficiency VARCHAR(100) NOT NULL,
    employee_salary DOUBLE NOT NULL
    );

-- Table: profile
CREATE TABLE IF NOT EXISTS profile (
                                       login_id INT PRIMARY KEY AUTO_INCREMENT,
                                       password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    employee_id INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE
    );

-- Table: project_employee
CREATE TABLE IF NOT EXISTS project_employee (
                                                employee_id INT NOT NULL,
                                                project_id INT NOT NULL,
                                                PRIMARY KEY (employee_id, project_id),
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
    );

-- Table: project_employee_task
CREATE TABLE IF NOT EXISTS project_employee_task (
                                                     employee_id INT NOT NULL,
                                                     project_id INT NOT NULL,
                                                     task_id INT NOT NULL,
                                                     PRIMARY KEY (employee_id, project_id, task_id),
    FOREIGN KEY (employee_id, project_id) REFERENCES project_employee (employee_id, project_id) ON DELETE CASCADE,
    FOREIGN KEY (task_id) REFERENCES task (task_id) ON DELETE CASCADE
    );

-- Table: project_employee_task_subTask
CREATE TABLE IF NOT EXISTS project_employee_task_subTask (
                                                             employee_id INT NOT NULL,
                                                             project_id INT NOT NULL,
                                                             task_id INT NOT NULL,
                                                             sub_task_id INT NOT NULL,
                                                             PRIMARY KEY (employee_id, project_id, task_id, sub_task_id),
    FOREIGN KEY (employee_id, project_id, task_id) REFERENCES project_employee_task (employee_id, project_id, task_id) ON DELETE CASCADE,
    FOREIGN KEY (sub_task_id) REFERENCES sub_task (sub_task_id) ON DELETE CASCADE
    );
