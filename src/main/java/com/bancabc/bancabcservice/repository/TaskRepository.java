package com.bancabc.bancabcservice.repository;

import com.bancabc.bancabcservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByPriority(String priority);

}
