package learn.fullstack.java.todo.service;

import learn.fullstack.java.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(TodoDto todoDto, Long id);

    void deleteTodo(long todoId);

    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);

}
