package com.steinledevops.swaggerplayground.service;

import com.steinledevops.swaggerplayground.request.TodoRequest;
import com.steinledevops.swaggerplayground.response.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse createTodo(TodoRequest request);
    List<TodoResponse> getAllTodos();
    TodoResponse getTodoById(Long id);
    TodoResponse updateTodo(Long id, TodoRequest request);
    void deleteTodo(Long id);
    TodoResponse completeTodo(Long id);
}
