package learn.fullstack.java.todo.repository;

import learn.fullstack.java.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}
