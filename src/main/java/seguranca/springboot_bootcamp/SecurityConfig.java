package seguranca.springboot_bootcamp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Implementación para buscar usuarios por nombre de usuario
        // Este es un ejemplo en memoria, en una aplicación real usarías un repositorio
        return username -> {
            if ("user".equals(username)) {
                return new org.springframework.security.core.userdetails.User(
                        "user", passwordEncoder().encode("password"),
                        java.util.List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER"))
                );
            } else if ("admin".equals(username)) {
                return new org.springframework.security.core.userdetails.User(
                        "admin", passwordEncoder().encode("admin123"),
                        java.util.List.of(
                                new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER"),
                                new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_ADMIN")
                        )
                );
            } else {
                throw new org.springframework.security.core.userdetails.UsernameNotFoundException("Usuario no encontrado: " + username);
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para este ejemplo (¡cuidado en producción!)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/public/**").permitAll() // Permitir acceso público a /public/**
                        .requestMatchers("/user/**").hasRole("USER") // Requiere rol USER para /user/**
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Requiere rol ADMIN para /admin/**
                        .anyRequest().authenticated() // Cualquier otra petición requiere autenticación
                )
                .formLogin(form -> form
                        .permitAll() // Permitir acceso al formulario de login
                        .defaultSuccessUrl("/home") // Redirigir a /home tras login exitoso
                        .failureUrl("/login?error") // Redirigir a /login?error si el login falla
                )
                .logout(logout -> logout
                        .permitAll() // Permitir acceso al formulario de logout
                        .logoutSuccessUrl("/login?logout") // Redirigir a /login?logout tras logout exitoso
                );

        return http.build();
    }
} 
