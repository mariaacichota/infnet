package com.infnet.aluno.config;

import com.infnet.aluno.repository.ProfessorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
class SecurityConfig {
    private final ProfessorRepository professorRepository;

    public SecurityConfig(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> professorRepository.findByUsername(username)
                .map(professor -> User.withUsername(professor.getUsername())
                        .password("{noop}" + professor.getPassword())
                        .roles("PROFESSOR")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Professor não encontrado"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }
}