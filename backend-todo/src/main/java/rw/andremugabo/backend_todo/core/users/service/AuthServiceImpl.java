package rw.andremugabo.backend_todo.core.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rw.andremugabo.backend_todo.core.role.model.Role;
import rw.andremugabo.backend_todo.core.role.reporsitory.RoleRepository;
import rw.andremugabo.backend_todo.core.users.dto.LoginDto;
import rw.andremugabo.backend_todo.core.users.dto.RegisterDto;
import rw.andremugabo.backend_todo.core.users.model.User;
import rw.andremugabo.backend_todo.core.users.repository.UserRepository;
import rw.andremugabo.backend_todo.exception.TodoAPIException;
import rw.andremugabo.backend_todo.security.JwtTokenProvider;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    // We have to inject Authentication manager dependency for login
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {

        // check username is already exist in database
        if(userRepository.existsByUsername(registerDto.getUsername()))
        {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        // check email is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail()))
        {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "User Email already exists");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager. authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
}
