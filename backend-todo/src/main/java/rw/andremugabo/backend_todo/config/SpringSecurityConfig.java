package rw.andremugabo.backend_todo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rw.andremugabo.backend_todo.security.JwtAuthenticationEntryPoint;
import rw.andremugabo.backend_todo.security.JwtAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {


    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthenticationFilter authenticationFilter;


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                            auth
//                                    .requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN")
//                                    .requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN")
//                                    .requestMatchers(HttpMethod.PATCH,"/api/**").hasRole("ADMIN")
//                                    .requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER")
//                                    .requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                                    .requestMatchers("/api/auth/**").permitAll()
                                    .requestMatchers(
                                            "/swagger-ui/**",
                                            "/swagger-ui.html",
                                            "/v3/api-docs/**",
                                            "/swagger-resources/**",
                                            "/webjars/**"
                                    ).permitAll()
                                    .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults());
        // Jwt related
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails ramesh = User.builder()
//                .username("ramesh")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(ramesh, admin);
//
//
//
//    }


}
