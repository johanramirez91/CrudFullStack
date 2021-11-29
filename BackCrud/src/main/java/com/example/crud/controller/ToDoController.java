package com.example.crud.controller;

import com.example.crud.DTO.DTOTodo;
import com.example.crud.domain.ToDo;
import com.example.crud.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoController {

    @Autowired
    private ToDoService service;

    @GetMapping(value = "api/todos")
    public Iterable<ToDo> list(){
        return service.list();
    }

    @PostMapping(value = "api/todo")
    public DTOTodo save(@RequestBody DTOTodo dtoTodo){
        return service.save(dtoTodo);
    }

    @PutMapping(value = "api/todo")
    public DTOTodo update(@RequestBody DTOTodo dtoTodo){
        if (dtoTodo.getId() != null){
            return service.save(dtoTodo);
        }
        throw new RuntimeException("No existe el id");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public DTOTodo get(@PathVariable("id") Long id){
        return service.get(id);
    }
}
