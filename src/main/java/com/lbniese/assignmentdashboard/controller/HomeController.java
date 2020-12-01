package com.lbniese.assignmentdashboard.controller;

import com.lbniese.assignmentdashboard.model.Assignment;
import com.lbniese.assignmentdashboard.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    AssignmentService assignmentService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/crud")
    public String crud(Model model) {
        logger.info("[GET] Calling fetch assignments");
        List<Assignment> assignmentList = assignmentService.fetchAll();
        model.addAttribute("assignments", assignmentList);
        return "crud";
    }

    @PostMapping("/crud")
    public String crud(@ModelAttribute Assignment assignment, Model model) {
        logger.info("[POST] Calling create assignment");
        assignmentService.create(assignment);
        List<Assignment> assignmentList = assignmentService.fetchAll();
        model.addAttribute("assignments", assignmentList);
        return "crud";
    }

    @GetMapping("/assignment/delete/{id}")
    public String deleteAssignment(@PathVariable("id") int id) {
        logger.info("[GET] Calling delete assignment");
        assignmentService.delete(id);
        return "redirect:/crud";
    }

    @GetMapping("/assignment/update/{id}")
    public String updateAssignment(@PathVariable("id") int id, Model model) {
        logger.info("[GET] Calling update assignment");
        model.addAttribute("assignment", assignmentService.fetchById(id));
        return "update-assignment";
    }

    @PostMapping("/crud/assignment/update")
    public String updateAssignment(@ModelAttribute Assignment assignment) {
        logger.info("[POST] Calling update assignment");
        assignmentService.update(assignment.getId(), assignment);
        return "redirect:/crud";
    }


}
