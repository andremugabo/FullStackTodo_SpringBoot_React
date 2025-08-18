package rw.andremugabo.backend_todo.controller.users;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.andremugabo.backend_todo.core.users.dto.JwtAuthResponse;
import rw.andremugabo.backend_todo.core.users.dto.LoginDto;
import rw.andremugabo.backend_todo.core.users.dto.RegisterDto;
import rw.andremugabo.backend_todo.core.users.service.AuthService;


@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name="User Management", description = "Operation for managing user for the Todo App")
public class AuthController {

    private final AuthService authService;


    // Build Register User REST API
    @Operation(
            summary = "Register User",
            description = "Register user with User Role "
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Build Login REST Api
    @Operation(
            summary = "User Login REST API",
            description = "User Login REST API for TODO APP"
    )
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody  LoginDto loginDto){
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }


}
