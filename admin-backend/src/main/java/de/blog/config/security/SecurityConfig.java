package de.blog.config.security;

import de.blog.data.tables.daos.UserEntityDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final SecurityUserService userService;
  private final JwtTokenUtil jwtTokenUtil;
  private final JwtTokenFilter jwtTokenFilter;
  private final UserEntityDao userEntityDao;
  private final SessionService sessionService;

  public SecurityConfig(
      final SecurityUserService userService,
      final JwtTokenUtil jwtTokenUtil,
      final JwtTokenFilter jwtTokenFilter,
      final UserEntityDao userEntityDao,
      final SessionService sessionService) {
    this.userService = userService;
    this.jwtTokenUtil = jwtTokenUtil;
    this.jwtTokenFilter = jwtTokenFilter;
    this.userEntityDao = userEntityDao;
    this.sessionService = sessionService;
  }

  @Bean
  protected PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  protected DaoAuthenticationProvider daoAuthenticationProvider() {
    final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

  @Bean
  protected SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {

    final UserLoginFilter userLoginFilter =
        new UserLoginFilter(
            daoAuthenticationProvider(), jwtTokenUtil, userEntityDao, sessionService);
    userLoginFilter.setFilterProcessesUrl("/api/login");

    http.cors(Customizer.withDefaults())
        .addFilter(userLoginFilter)
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            m ->
                m.requestMatchers("/api/login", "/api/authentication/**")
                    .permitAll()
                    .anyRequest()
                    .hasRole("ADMIN"))
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
