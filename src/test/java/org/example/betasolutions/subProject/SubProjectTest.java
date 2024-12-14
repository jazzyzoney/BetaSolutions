package org.example.betasolutions.subProject;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class SubProjectTest {

    @Test
    void setHours() {
        SubProject subProject = new SubProject("sub1", 3000,2500, Date.valueOf("2021-01-01"),1);
        assertTrue(subProject.getDays()>0);

    }
}