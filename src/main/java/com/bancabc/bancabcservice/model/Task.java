package com.bancabc.bancabcservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.text.SimpleDateFormat;

@Data
@Entity
public class Task  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String priority;
    private String dueDate;

    public void setTitle(String title) throws Exception {
        if(title==null || title.isEmpty())
            throw new Exception("Title Cannot Be Empty.");
        this.title = title;
    }

    public void setDescription(String description) throws Exception {
        if(description==null || description.isEmpty())
            throw new Exception("Description Cannot Be Empty.");
        this.description = description;
    }

    public void setPriority(String priority) throws Exception {
        if(priority==null || priority.isEmpty())
            throw new Exception("Priority Cannot Be Empty.");
        try {
            Priority.valueOf(priority);
        }catch (Exception ex){
            throw new Exception("Invalid Priority, It can only be HIGH, MEDIUM or LOW.");
        }
        this.priority = priority;
    }

    public void setDueDate(String dueDate) throws Exception {
        if(dueDate==null || dueDate.isEmpty())
            throw new Exception("Due Date Cannot Be Empty.");
        this.dueDate = dueDate;
    }
}
