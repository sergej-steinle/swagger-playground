package com.steinledevops.swaggerplayground.service;

import com.steinledevops.swaggerplayground.entity.Todo;
import com.steinledevops.swaggerplayground.entity.User;
import com.steinledevops.swaggerplayground.repository.TodoRepository;
import com.steinledevops.swaggerplayground.request.TodoRequest;
import com.steinledevops.swaggerplayground.response.TodoResponse;
import com.steinledevops.swaggerplayground.util.GetAuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final GetAuthenticatedUser getAuthenticatedUser;

    @Override
    @Transactional
    public TodoResponse createTodo(TodoRequest request) {
        User currentUser = getAuthenticatedUser.getUser();

        Todo todo = Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .owner(currentUser)
                .build();

        todoRepository.save(todo);

        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .priority(todo.getPriority())
                .completed(todo.isCompleted())
                .build();
    }

    @Override
    public List<TodoResponse> getAllTodos() {
        User currentUser = getAuthenticatedUser.getUser();
        List<Todo> todos = todoRepository.findAllByOwner(currentUser);

        return todos.stream()
                .map(todo -> TodoResponse.builder()
                        .id(todo.getId())
                        .title(todo.getTitle())
                        .description(todo.getDescription())
                        .priority(todo.getPriority())
                        .completed(todo.isCompleted())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public TodoResponse getTodoById(Long id) {
        User currentUser = getAuthenticatedUser.getUser();
        Todo todo = todoRepository.findByIdAndOwner(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .priority(todo.getPriority())
                .completed(todo.isCompleted())
                .build();
    }

    @Override
    @Transactional
    public TodoResponse updateTodo(Long id, TodoRequest request) {
        User currentUser = getAuthenticatedUser.getUser();
        Todo todo = todoRepository.findByIdAndOwner(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setPriority(request.getPriority());

        todoRepository.save(todo);

        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .priority(todo.getPriority())
                .completed(todo.isCompleted())
                .build();
    }

    @Override
    @Transactional
    public void deleteTodo(Long id) {
        User currentUser = getAuthenticatedUser.getUser();
        Todo todo = todoRepository.findByIdAndOwner(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        todoRepository.delete(todo);
    }

    @Override
    @Transactional
    public TodoResponse completeTodo(Long id) {
        User currentUser = getAuthenticatedUser.getUser();
        Todo todo = todoRepository.findByIdAndOwner(id, currentUser)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        todo.setCompleted(true);
        todoRepository.save(todo);

        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .priority(todo.getPriority())
                .completed(todo.isCompleted())
                .build();
    }
}
