package com.system.remedios.Controller;

import com.system.remedios.config.DataAuthentication;
import com.system.remedios.service.TokenService;
import com.system.remedios.domain.Usuario;
import com.system.remedios.tokenDto.DataTokenJwt;
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

    @PostMapping
    public ResponseEntity<DataTokenJwt> makeLogin(@RequestBody @Valid DataAuthentication data){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(data.username(), data.password());

        Authentication authenticate = authenticationManager.authenticate(login);

        String tokenJwt = tokenService.generateToken((Usuario) authenticate.getPrincipal());

        return ResponseEntity.ok(new DataTokenJwt(tokenJwt));
    }

}
