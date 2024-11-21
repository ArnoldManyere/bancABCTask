package com.bancabc.bancabcservice.controller;

import com.bancabc.bancabcservice.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class ProcessRequestController {

    private static Logger logger = Logger.getLogger(ProcessRequestController.class.getName());

    @Autowired
    private ToDoListService toDoListService;

    @PostMapping({"/addNewTask"})
    public Map addNewTask(@RequestBody(required = false) Map request) throws Exception {
        logger.info("Add New Task : " + request);
        return toDoListService.addTask(request);
    }

    @PostMapping({"/editTask"})
    public Map editTask(@RequestBody(required = false) Map request) throws Exception {
        logger.info("Edit Task : " + request);
        return toDoListService.editTask(request);
    }

    @PostMapping({"/deleteTask"})
    public Map deleteTask(@RequestBody(required = false) Map request) throws Exception {
        logger.info("Delete Task : " + request);
        return toDoListService.deleteTask(request);
    }

    @PostMapping({"/viewTasks"})
    public Map viewTasks(@RequestBody(required = false) Map request) throws Exception {
        logger.info("View Tasks : " + request);
        return toDoListService.viewTaskByPriority(request);
    }

}
