package com.example.spring_cook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        //если пользователь перешел по antMatchers разрешает всем(permitAll())
                        .antMatchers("/", "/firstA", "/firstB", "/firstC", "/firstD",
                                "/secondA", "/secondB", "/secondC", "/secondD", "/secondE", "/thirdA",
                                "/thirdB").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build();

        UserDetails manager =
                User.withDefaultPasswordEncoder()
                        .username("manager")
                        .password("password")
                        .roles("MANAGER")
                        .build();

        InMemoryUserDetailsManager detailsService = new InMemoryUserDetailsManager();
        detailsService.createUser(user);
        detailsService.createUser(admin);
        detailsService.createUser(manager);
        return detailsService;
    }
}
