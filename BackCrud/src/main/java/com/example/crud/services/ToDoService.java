package com.example.crud.services;

import com.example.crud.DTO.DTOTodo;
import com.example.crud.domain.ToDo;
import com.example.crud.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository repository;

    public Iterable<ToDo> list(){
        return repository.findAll();
    }

    public DTOTodo save(DTOTodo dtoTodo){
        ToDo todo = new ToDo();
        todo = dtoTodo.convertInTODO();
        repository.save(todo);
        dtoTodo.convertInDTO(todo);
        return dtoTodo;
    }

    public DTOTodo get(Long id){
        DTOTodo dtoTodo = new DTOTodo();
        dtoTodo.convertInDTO(repository.findById(id).orElseThrow());
        return dtoTodo;
    }

    public void delete(Long id){
        repository.delete(get(id).convertInTODO());
    }
}
