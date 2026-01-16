package learn.fullstack.java.todo.controller;

import learn.fullstack.java.todo.dto.TodoDto;
import learn.fullstack.java.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    // create an add Todo endpoint
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }


    // build GET Todo REST API
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // build a get All Todos API
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> allTodos = todoService.getAllTodos();
        // return new ResponseEntity<>(todos. HttpStatus.OK); -- Alternative of below statement
        return ResponseEntity.ok(allTodos);
    }

    //build a Update API for Todo
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long todoId) {
        TodoDto updatedDto = todoService.updateTodo(todoDto, todoId);

        return ResponseEntity.ok(updatedDto);
    }

    //build a DELETE API for Todo
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo point deleted successfully");
    }

    // build a patch endpoint for completed Todo
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
        TodoDto completedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(completedTodo);
    }

    // build a patch endpoint for inCompleted Todo
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId) {
        TodoDto inCompletedTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(inCompletedTodo);
    }

}
