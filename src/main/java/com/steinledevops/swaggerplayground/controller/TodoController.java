package com.steinledevops.swaggerplayground.controller;

import com.steinledevops.swaggerplayground.request.TodoRequest;
import com.steinledevops.swaggerplayground.response.TodoResponse;
import com.steinledevops.swaggerplayground.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Todo REST endpoints", description = "Endpoints for managing todos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "Create a new todo")
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest request) {
        TodoResponse response = todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all todos for the authenticated user")
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        List<TodoResponse> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @Operation(summary = "Get a todo by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
        TodoResponse response = todoService.getTodoById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a todo")
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequest request) {
        TodoResponse response = todoService.updateTodo(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a todo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Mark a todo as completed")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoResponse> completeTodo(@PathVariable Long id) {
        TodoResponse response = todoService.completeTodo(id);
        return ResponseEntity.ok(response);
    }
}
