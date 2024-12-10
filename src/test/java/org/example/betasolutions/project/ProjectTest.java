package org.example.betasolutions.project;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void setTotalHours() {
        Project project = new Project("project_test","Owner",2500,10.000, Date.valueOf("2021-01-01"));
        project.setTotalHours(2000);
        assertTrue(project.getTotalDays()>0);

    }
}