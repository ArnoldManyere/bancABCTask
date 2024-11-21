package com.bancabc.bancabcservice.service.impl;

import com.bancabc.bancabcservice.controller.ProcessRequestController;
import com.bancabc.bancabcservice.model.Task;
import com.bancabc.bancabcservice.repository.TaskRepository;
import com.bancabc.bancabcservice.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    private static Logger logger = Logger.getLogger(ToDoListServiceImpl.class.getName());

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Map addTask(Map map) {
        //Getting data from the Map to Variables
        String title = map.get("title").toString();
        String description = map.get("description").toString();
        String dueDate = map.get("dueDate").toString();
        String priority = map.get("priority").toString();

        Map response = new HashMap();
        Task newTask = new Task();

        //Setting Task Fields
        try{
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setDueDate(dueDate);
        newTask.setPriority(priority);
        }catch (Exception ex){
            response.put("responseMessage", "ERROR - " + ex.getMessage());
            return response;
        }
        //Saving New Task
        taskRepository.save(newTask);
        logger.info("New Task Saved.");

        response.put("responseMessage", "New Task Added.");
        return response;
    }

    @Override
    public Map editTask(Map map) {

        //First Get Task ID
        String id = map.get("taskId").toString();
        logger.info("Updating Task with ID : " + id);

        //This is the name of the field to be edited
        String fieldName = map.get("fieldName").toString();

        //The value to be updated with
        String fieldValue = map.get("fieldValue").toString();

        //Check if the task Exist
        Map response = new HashMap();
        Task existingTask = taskRepository.findById(Long.valueOf(id)).get();
        if(existingTask==null){
            response.put("responseMessage", "ERROR - Task does not exist.");
            return response;
        }

        //Checking the field to be Updated.
        try{
        if(fieldName.equalsIgnoreCase("title"))
            existingTask.setTitle(fieldValue);
        else if(fieldName.equalsIgnoreCase("description"))
            existingTask.setDescription(fieldValue);
        else if(fieldName.equalsIgnoreCase("dueDate"))
            existingTask.setDueDate(fieldValue);
        else if(fieldName.equalsIgnoreCase("priority"))
            existingTask.setPriority(fieldValue);
        else {
            response.put("responseMessage", "ERROR - Field Does not Exist.");
            return response;
        }
        }catch (Exception ex){
            response.put("responseMessage", "ERROR - " + ex.getMessage());
            return response;
        }

        //Saving the Changes
        taskRepository.save(existingTask);

        response.put("responseMessage", "SUCCESS - Task Updated.");
        return response;
    }

    @Override
    public Map deleteTask(Map map) {
        //First Get Task ID
        String id = map.get("taskId").toString();
        logger.info("Deleting Task with ID : " + id);

        //Delete Task with ID
        taskRepository.deleteById(Long.valueOf(id));

        Map response = new HashMap();
        response.put("responseMessage", "SUCCESS - Task Deleted.");
        return response;
    }

    @Override
    public Map viewTaskByPriority(Map map) {
        String priority = map.get("priority").toString();
        List<Task> tasks = taskRepository.findByPriority(priority);
        Map response = new HashMap();
        response.put("tasks", tasks);
        return response;
    }
}
