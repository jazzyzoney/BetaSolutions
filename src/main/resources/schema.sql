-- Drop tables if they exist (H2 syntax)
DROP TABLE IF EXISTS Profile;

-- Create tables
CREATE TABLE Profile (
                         profile_id INT PRIMARY KEY AUTO_INCREMENT,
                         username VARCHAR(45) NOT NULL,
                         password VARCHAR(20) NOT NULL,
                         profile_email VARCHAR(45) NOT NULL,
                         profile_phone VARCHAR(45) NOT NULL,
                         UNIQUE (username),
                         UNIQUE (profile_email),
                         UNIQUE (profile_phone)
);

-- Insert sample data into project table
INSERT INTO project (project_name, project_owner, project_total_hours, project_total_days, project_total_price, project_deadline, project_start_date, project_active)
VALUES
    ('Project A', 'Owner 1', 500, 30, 10000.0, '2024-12-31', '2024-11-01', 1),
    ('Project B', 'Owner 2', 300, 20, 8000.0, '2025-01-15', '2024-12-01', 1);

-- Insert sample data into sub_project table
INSERT INTO sub_project (sub_project_name, sub_project_total_hours, sub_project_total_days, sub_project_total_price, sub_project_deadline, sub_project_start_date, project_id, sub_project_active)
VALUES
    ('SubProject 1', 200, 15, 5000.0, '2024-12-15', '2024-11-05', 1, 1),
    ('SubProject 2', 100, 10, 3000.0, '2025-01-05', '2024-12-10', 2, 1);

-- Insert sample data into task table
INSERT INTO task (task_name, task_total_hours, task_total_days, task_total_price, task_deadline, task_start_date, project_id, sub_project_id, task_active)
VALUES
    ('Task 1', 100, 5, 1500.0, '2024-12-05', '2024-11-10', 1, 1, 1),
    ('Task 2', 50, 4, 1200.0, '2024-12-20', '2024-11-15', 2, 2, 1);

-- Insert sample data into employee table
INSERT INTO employee (employee_name, employee_office, employee_proficiency, employee_salary)
VALUES
    ('Employee 1', 'Office 1', 'Developer', 5000.0),
    ('Employee 2', 'Office 2', 'Designer', 4000.0);

-- Insert sample data into sub_task table
INSERT INTO sub_task (sub_task_name, sub_task_total_hours, sub_task_total_days, sub_task_total_price, sub_task_deadline, sub_task_start_date, task_id, sub_task_active)
VALUES
    ('SubTask 1', 50, 3, 500.0, '2024-12-03', '2024-11-10', 1, 1),
    ('SubTask 2', 25, 2, 300.0, '2024-12-18', '2024-11-15', 2, 1);

-- Insert sample data into profile table
INSERT INTO profile (password, email, employee_id)
VALUES
    ('password1', 'employee1@example.com', 1),
    ('password2', 'employee2@example.com', 2);
