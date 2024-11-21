package com.bancabc.bancabcservice.service;

import java.util.List;
import java.util.Map;

public interface ToDoListService {

    /** This method is for adding new Task **/
    Map addTask(Map map);

    /** This method is for editing Task **/
    Map editTask(Map map);

    /** This method is for deleting Task **/
    Map deleteTask(Map map);

    /** This method is for view Tasks by Priority **/
    Map viewTaskByPriority(Map map);

}
