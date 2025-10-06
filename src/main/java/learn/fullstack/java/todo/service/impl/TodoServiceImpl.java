package learn.fullstack.java.todo.service.impl;

import learn.fullstack.java.todo.dto.TodoDto;
import learn.fullstack.java.todo.entity.Todo;
import learn.fullstack.java.todo.repository.TodoRepository;
import learn.fullstack.java.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;


    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert TodoDto to Todo JPA entity
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        // save Todo JPA entity to database
        Todo savedTodo = todoRepository.save(todo);

        // convert saved Todo JPA entity to TodoDto
        TodoDto savedTodoDto = new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodo.isCompleted());

        return savedTodoDto;
    }
}
