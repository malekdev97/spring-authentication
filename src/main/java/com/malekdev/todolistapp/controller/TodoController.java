package com.malekdev.todolistapp.controller;

import com.malekdev.todolistapp.model.TodoItem;
import com.malekdev.todolistapp.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

// Request mapping handling the get method http
@RestController
@RequestMapping( value = "/todo")
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @GetMapping
    public List<TodoItem> findAll(){

        return todoRepo.findAll();
    }

    @PostMapping
    public TodoItem save(@RequestBody TodoItem todoItem){
        return todoRepo.save(todoItem);
    }

    @PutMapping
    public TodoItem update(@Valid @NotNull @RequestBody TodoItem todoItem){

        return todoRepo.save(todoItem);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable long id){
        todoRepo.deleteById(id);
    }
}
