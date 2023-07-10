package fr.maetic.security.configuration;

import fr.maetic.service.securite.JpaUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
@Configuration
public class MyAuthenticationProvider {
    private final JpaUserDetailsService jpaUserDetailsService;
    private final PasswordConfig passwordConfig;


    public MyAuthenticationProvider(JpaUserDetailsService jpaUserDetailsService, PasswordConfig passwordConfig) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.passwordConfig = passwordConfig;
    }
    //@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
    final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

    authenticationProvider.setUserDetailsService(jpaUserDetailsService);
    authenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());

    return authenticationProvider;
    }
}