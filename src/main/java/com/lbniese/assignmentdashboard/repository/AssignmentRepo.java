package com.lbniese.assignmentdashboard.repository;

import com.lbniese.assignmentdashboard.model.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.lbniese.assignmentdashboard.controller.HomeController.logger;

@Repository
public class AssignmentRepo {

    @Autowired
    JdbcTemplate template;

    public void create(Assignment assignment) {
        try {
            String sql = "INSERT INTO Assignment (name, description) VALUES(?,?)";
            template.update(sql, assignment.getName(), assignment.getDescription());
            if (assignment.getName().length() > 50) {
                throw new DataIntegrityViolationException("Name Too Long For Column!");
            }
            if (assignment.getDescription().length() > 100) {
                throw new DataIntegrityViolationException("Description Too Long For Column!");
            }
        } catch (DataIntegrityViolationException e) {
            logger.error("Data Too Long For Column Exception!");
        }
    }

    public List<Assignment> fetchAll() {
        String sql = "SELECT * FROM Assignment";
        RowMapper<Assignment> rowMapper = new BeanPropertyRowMapper<>(Assignment.class);
        return template.query(sql, rowMapper);
    }

    public Assignment fetchById(int id) {
        String sql = "SELECT * FROM Assignment WHERE id = ?";
        RowMapper<Assignment> rowMapper = new BeanPropertyRowMapper<>(Assignment.class);
        Assignment assignment = template.queryForObject(sql, rowMapper, id);
        return assignment;
    }

    public List<Assignment> fetchByName(String name) {
        String sql = "SELECT * FROM Assignment WHERE name = ?";
        RowMapper<Assignment> rowMapper = new BeanPropertyRowMapper<>(Assignment.class);
        return template.query(sql, rowMapper, name);
    }

    public Boolean delete(int id) {
        String sql = "DELETE FROM Assignment WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    public void update(int id, Assignment assignment) {
        try {
            String sql = "UPDATE Assignment SET name=?, description=? WHERE id=?";
            template.update(sql, assignment.getName(), assignment.getDescription(), id);
            if (assignment.getName().length() > 50) {
                throw new DataIntegrityViolationException("Name Too Long For Column!");
            }
            if (assignment.getDescription().length() > 100) {
                throw new DataIntegrityViolationException("Description Too Long For Column!");
            }
        } catch (DataIntegrityViolationException e) {
            logger.error("Data Too Long For Column Exception!");
        }
    }


}
