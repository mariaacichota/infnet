package com.infnet.aluno.config;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final ProfessorRepository professorRepository;

    public SecurityConfig(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> professorRepository.findByUsername(username)
                .map(professor -> User.withUsername(professor.getUsername())
                        .password("{noop}" + professor.getPassword()) // {noop} = senha em texto puro
                        .roles("PROFESSOR")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Professor não encontrado"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
}