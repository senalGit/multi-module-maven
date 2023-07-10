package fr.maetic.security.configuration;

import fr.maetic.service.securite.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {
    private final JpaUserDetailsService jpaUserDetailsService;
    private final JwtAuthenticationFilter filter;
    private final PasswordConfig passwordConfig;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService, JwtAuthenticationFilter filter,
            PasswordConfig passwordConfig) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.filter = filter;
        this.passwordConfig = passwordConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                // .requestMatchers(new AntPathRequestMatcher("/**'/'auth/**")).permitAll() enlever les ' ' sur les /
                // .requestMatchers(new AntPathRequestMatcher("/**'/'")).permitAll()
                // .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                // .anyRequest().authenticated())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(jpaUserDetailsService)
                .authenticationProvider(authenticationProvider())
                .httpBasic(Customizer.withDefaults())
                .build();

/*    http.securityMatcher(EndpointRequest.toAnyEndpoint());
        http.authorizeHttpRequests((requests) ->
            requests.anyRequest().hasRole("ENDPOINT_ADMIN"));
        http.httpBasic(withDefaults());
        return http.build();

        
          http.csrf()
          .disable()
          .authorizeRequests()
          .antMatchers(HttpMethod.DELETE)
          .hasRole("ADMIN")
          .antMatchers("/admin/**")
          .hasAnyRole("ADMIN")
          .antMatchers("/user/**")
          .hasAnyRole("USER", "ADMIN")
          .antMatchers("/login/**")
          .anonymous()
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic()
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
          
          return http.build();*/
         

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(jpaUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

}
