package com.system.remedios.security;

import com.system.remedios.Repository.UserRepository;
import com.system.remedios.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter{ //go to pull once filter each request
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenJWT = getToken(request);
        if(tokenJWT != null){
            String subject = tokenService.getSubject(tokenJWT);

            UserDetails user = userRepository.findByUsername(subject); //find for the username, if there is one

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            //see if the username is authenticated and get authorities

            SecurityContextHolder.getContext().setAuthentication(authentication);
            //authenticating the user in spring security and free the access

        }
        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}
