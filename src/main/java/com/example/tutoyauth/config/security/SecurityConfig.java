package com.example.tutoyauth.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * ignore h2-console
   * @return WebSecurityCustomizer
   */
//  @Bean
//  @ConditionalOnProperty(name = "spring.h2.console.enabled", havingValue = "true")
//  public WebSecurityCustomizer configureH2ConsoleEnable() {
//    return web -> web.ignoring()
//        .requestMatchers(PathRequest.toH2Console());
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
        .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin))
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/**").permitAll()
            .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults());
    return http.build();
  }
}
