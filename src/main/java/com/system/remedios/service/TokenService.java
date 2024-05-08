package com.system.remedios.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.system.remedios.domain.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("api.security.token.secret=${JWT_SECRET:123456}") //form of identify the variable in properties
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return  JWT.create()
                    .withIssuer("Medicine_api")//creator
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dateExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error the generation of the token", exception);
        }
    }
    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Medicine_api")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Error token is invalid", exception);
        }
    }

    public Instant dateExpiration (){
        return  LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); //zone in Brazil -03:00
    }
}
