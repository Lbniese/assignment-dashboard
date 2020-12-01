package com.lbniese.assignmentdashboard.controller;

import com.lbniese.assignmentdashboard.model.Assignment;
import com.lbniese.assignmentdashboard.repository.AssignmentRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HomeControllerTest {

    @Autowired
    HomeController homeController;

    @Autowired
    AssignmentRepo assignmentRepo;

    @Mock
    Model model;

    @Test
    public void testGetIndex() {
        assertEquals("index", homeController.index());
    }

    @Test
    public void testGetCrud() {
        assertEquals("crud", homeController.crud(model));
    }

    @Test
    public void testGetDeleteAssignment() {
        assignmentRepo.create(new Assignment("Test Name", "Test Desc"));
        List<Assignment> assignments = assignmentRepo.fetchByName("Test Name");
        assertEquals("redirect:/crud", homeController.deleteAssignment(assignments.get(0).getId()));
    }

    @Test
    public void testGetUpdateAssignment() {
        assignmentRepo.create(new Assignment("Test Name", "Test Desc"));
        List<Assignment> assignments = assignmentRepo.fetchByName("Test Name");
        assertEquals("update-assignment", homeController.updateAssignment(assignments.get(0).getId(), model));
    }

}
