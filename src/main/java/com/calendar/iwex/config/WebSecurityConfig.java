package com.calendar.iwex.config;


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
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/calendar","/saveEvent","/page/**").permitAll()
                        .antMatchers("/", "/login", "/logout", "/error").permitAll()
                        .antMatchers("/css/**","/src/**","/vendors/**","/static/**", "/resources/**","/*.css","/*.js").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )

                        .logout()
                .deleteCookies("JSESSIONID")
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll();


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("pikkasopaulo93")
                        .roles("ADMIN")
                        .build();
        UserDetails manager =
                User.withDefaultPasswordEncoder()
                        .username("manager")
                        .password("bishkek312iwex")
                        .roles("MANAGER")
                        .build();

        return new InMemoryUserDetailsManager(user,manager);
    }
}
