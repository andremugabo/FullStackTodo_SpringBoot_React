package rw.andremugabo.backend_todo.core.users.service;

import rw.andremugabo.backend_todo.core.users.dto.LoginDto;
import rw.andremugabo.backend_todo.core.users.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
