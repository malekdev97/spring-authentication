package com.malekdev.todolistapp.repo;


import com.malekdev.todolistapp.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoItem, Long> {
    // is talking to the DataBase!


}
