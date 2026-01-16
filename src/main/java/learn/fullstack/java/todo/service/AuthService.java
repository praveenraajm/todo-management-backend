package learn.fullstack.java.todo.service;

import learn.fullstack.java.todo.dto.LoginDto;
import learn.fullstack.java.todo.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}