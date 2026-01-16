package learn.fullstack.java.todo.service.impl;

import learn.fullstack.java.todo.dto.TodoDto;
import learn.fullstack.java.todo.entity.Todo;
import learn.fullstack.java.todo.exception.ResourceNotFoundException;
import learn.fullstack.java.todo.repository.TodoRepository;
import learn.fullstack.java.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

//     convert TodoDto to Todo JPA entity
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());

//    convert TodoDto to Todo JPA entity using model mapper
        Todo todo = modelMapper.map(todoDto, Todo.class);

        // save Todo JPA entity to database
        Todo savedTodo = todoRepository.save(todo);

//     convert saved Todo JPA entity to TodoDto
//        TodoDto savedTodoDto = new TodoDto();
//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodo.isCompleted());

//    convert TodoDto to Todo JPA entity using model mapper
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                      .orElseThrow(() -> new ResourceNotFoundException("Todo not found with Id -->" + id));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todoList = todoRepository.findAll();

        return todoList.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provided Todo Id does not exists --> " + id));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(long todoId) {
        Todo todo = todoRepository.findById(todoId)
                     .orElseThrow(() -> new ResourceNotFoundException("Provided Todo Id does not exists --> " + todoId));

        todoRepository.deleteById(todoId);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provided Todo Id does not exists --> " + id));

        todo.setCompleted(Boolean.TRUE);

        todoRepository.save(todo);

         return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provided Todo Id does not exists --> " + id));

        todo.setCompleted(Boolean.FALSE);

        todoRepository.save(todo);

        return modelMapper.map(todo, TodoDto.class);
    }
}
