package com.system.remedios.Controller;

import com.system.remedios.domain.Usuario;
import com.system.remedios.requests.DataAuthentication;
import com.system.remedios.service.TokenService;
import com.system.remedios.tokenDto.DataTokenJwt;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @Operation(summary = "Doing login of user",
            description ="Doing login of user",
            tags = {"Login"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @PostMapping
    public ResponseEntity<DataTokenJwt> doLogin(@RequestBody @Valid DataAuthentication data){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(data.username(), data.password());

        Authentication authenticate = authenticationManager.authenticate(login);

        String tokenJwt = tokenService.generateToken((Usuario) authenticate.getPrincipal());

        return ResponseEntity.ok(new DataTokenJwt(tokenJwt));
    }

}
