package com.system.remedios.Controller;

import com.system.remedios.security.DataAuthentication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> makeLogin(@RequestBody @Valid DataAuthentication data){
        var login = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authenticate = authenticationManager.authenticate(login);
        return ResponseEntity.ok().build();
    }

}
