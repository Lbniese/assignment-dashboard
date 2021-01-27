package com.lbniese.assignmentdashboard.repository;

import com.lbniese.assignmentdashboard.model.Assignment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AssignmentRepoTest {

    @Autowired
    AssignmentRepo assignmentRepo;

    @Test
    public void testCreateAssignment() {
        assignmentRepo.create(new Assignment("Test Name", "Test Desc"));
        List<Assignment> assignments = assignmentRepo.fetchByName("Test Name");
        assertEquals("Test Name", assignments.get(0).getName());
    }

    @Test
    public void testExistsFetchAssignmentById() {
        Assignment assignment = assignmentRepo.fetchById(1);

        assertNotNull(assignment);
        assertTrue(assignment.getId() > 0);

    }

    @Test
    public void testIdFetchAssignmentById() {
        Assignment assignment = assignmentRepo.fetchById(1);

        assertEquals(1, assignment.getId());
    }

    @Test
    public void testNameFetchAssignmentById() {
        Assignment assignment = assignmentRepo.fetchById(1);

        assertEquals("Web Server", assignment.getName());
    }

    @Test
    public void testDescFetchAssignmentById() {
        Assignment assignment = assignmentRepo.fetchById(1);

        assertEquals("Create Web Server", assignment.getDescription());
    }

    @Test
    public void testDeleteAssignment() {
        assignmentRepo.create(new Assignment("Test Name", "Test Desc"));
        List<Assignment> assignments = assignmentRepo.fetchByName("Test Name");
        assignmentRepo.delete(assignments.get(0).getId());
        assignments = assignmentRepo.fetchByName("Test Name");
        assertEquals(0, assignments.size());

    }

    @Test
    public void testUpdateAssignment() {
        assignmentRepo.create(new Assignment("Test Name", "Test Desc"));
        List<Assignment> assignments = assignmentRepo.fetchByName("Test Name");
        assignments.get(0).setName("Updated Test Name");
        assignmentRepo.update(assignments.get(0).getId(), assignments.get(0));
        assignments = assignmentRepo.fetchByName("Updated Test Name");
        assertEquals("Updated Test Name", assignments.get(0).getName());
    }

    @Test
    public void testFetchAllAssignments() {
        assignmentRepo.create(new Assignment("Test Name 1", "Test Desc 1"));
        assignmentRepo.create(new Assignment("Test Name 2", "Test Desc 2"));
        List<Assignment> assignments = assignmentRepo.fetchAll();
        assertTrue(assignments.size() >= 2);
    }
}