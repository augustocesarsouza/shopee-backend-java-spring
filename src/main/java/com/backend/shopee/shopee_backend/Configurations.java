package com.backend.shopee.shopee_backend;

import com.backend.shopee.shopee_backend.data.authentication.FilterToken;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class Configurations {
    private FilterToken filter;
    @Value("${CLOUD-NAME}")
    private String CLOUD_NAME;
    @Value("${API-KEY}")
    private String API_KEY;
    @Value("${API-SECRET}")
    private String API_SECRET;
    @Value("${FRONTEND_URL}")
    private String frontendUrl;

    @Autowired
    public Configurations(FilterToken filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.addAllowedOrigin(frontendUrl);  // Permitir apenas o frontend
                    corsConfig.addAllowedMethod(HttpMethod.GET);
                    corsConfig.addAllowedMethod(HttpMethod.POST);
                    corsConfig.addAllowedMethod(HttpMethod.PUT);
                    corsConfig.addAllowedMethod(HttpMethod.DELETE);
                    corsConfig.addAllowedHeader("*");
                    corsConfig.setAllowCredentials(true);  // Permitir credenciais (cookies, tokens)
                    return corsConfig;
                }))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(HttpMethod.GET, "/v1/public/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/v1/public/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/v1/public/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/v1/public/**").permitAll()
                                .anyRequest().authenticated()).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // isso é para AuthenticationManager authenticationManager, lá no Controller
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);

        return new Cloudinary(config);
    }
}