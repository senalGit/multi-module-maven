package fr.maetic.security.configuration;

import fr.maetic.service.securite.JpaUserDetailsService;
import fr.maetic.service.securite.SecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JpaUserDetailsService jpaUserDetailsService;
    private final SecurityService securityService;
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JpaUserDetailsService jpaUserDetailsService, SecurityService securityService, JwtUtils jwtUtils) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.securityService = securityService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authenticationHeader = request.getHeader(AUTHORIZATION);
        final String username;
        final String jwtToken;

        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authenticationHeader.substring(7);
        username = jwtUtils.extractUsername(jwtToken);


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(username);


            if (Boolean.TRUE.equals(jwtUtils.isTokenValid(jwtToken, userDetails))) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
