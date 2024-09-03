package com.hospital.hospital.config;
/*

import com.hospital.hospital.service.user.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private MyUserDetailService userDetailService;

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity; enable it with proper configuration in production

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("src/main/webapp/WEB-INF/views/login.jsp" , "/resources/static/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/resource").permitAll() // Allow GET requests to /api/resource
                        .requestMatchers(HttpMethod.POST, "/api/resource").hasRole("ADMIN") // Allow POST requests to /api/resource for users with ADMIN role
                        .requestMatchers(HttpMethod.PUT, "/api/resource").hasRole("ADMIN") // Allow PUT requests to /api/resource for users with ADMIN role
                        .requestMatchers(HttpMethod.DELETE, "/api/resource").hasRole("ADMIN") // Allow TO DELETE requests to /api/resource for users with ADMIN role
                        .anyRequest().authenticated()) // All other requests need to be authenticated

                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .loginProcessingUrl("/authenticate") // This should be different from the login page URL
                        .defaultSuccessUrl("/home", true) // Redirect to a home page after successful login
                        .failureUrl("/login?error=true") // Redirect to the login page with an error message on failure
                        .permitAll())

                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                        .permitAll())

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
*/
