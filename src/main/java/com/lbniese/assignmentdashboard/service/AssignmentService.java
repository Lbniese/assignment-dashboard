package com.lbniese.assignmentdashboard.service;

import com.lbniese.assignmentdashboard.model.Assignment;
import com.lbniese.assignmentdashboard.repository.AssignmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    AssignmentRepo assignmentRepo;

    public void create(Assignment assignment) {
        assignmentRepo.create(assignment);
    }

    public List<Assignment> fetchAll() {
        return assignmentRepo.fetchAll();
    }

    public Assignment fetchById(int id) {
        return assignmentRepo.fetchById(id);
    }

    public Boolean delete(int id) {
        try {
            return assignmentRepo.delete(id);
        } catch (DataIntegrityViolationException ex) {
            return false;
        }
    }

    public void update(int id, Assignment assignment) {
        assignmentRepo.update(id, assignment);
    }


}
