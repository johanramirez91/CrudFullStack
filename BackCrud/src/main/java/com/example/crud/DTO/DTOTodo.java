package com.example.crud.DTO;

import com.example.crud.domain.ToDo;

public class DTOTodo {

    private Long id;
    private String name;
    private boolean completed;
    private  String groupListId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getGroupListId() {
        return groupListId;
    }

    public ToDo convertInTODO() {
        ToDo todo = new ToDo();
        todo.setName(name);
        todo.setId(id);
        todo.setCompleted(completed);
        todo.setGroupListId(groupListId);
        return todo;
    }

    public void convertInDTO(ToDo todo){
        name = todo.getName();
        id = todo.getId();
        completed = todo.isCompleted();
        groupListId = todo.getGroupListId();
    }
}
