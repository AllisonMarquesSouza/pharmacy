package com.system.remedios.Controller;

import com.system.remedios.domain.User;
import com.system.remedios.dtos.DataAuthentication;
import com.system.remedios.dtos.ResponseJwtToken;
import com.system.remedios.dtos.UserDtoPost;
import com.system.remedios.dtos.UserDtoPut;
import com.system.remedios.security.TokenService;
import com.system.remedios.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request, param invalid", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "401", description = "Unauthorized, the user didn't authenticate", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "403", description = "Forbidden, you don't have permission", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "404", description = "Not found ", content = @Content(schema = @Schema())),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema()))
})
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthenticationService authenticationService;


    @Operation(summary =  "Login with user", method = "POST", description ="Method to login a user", responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @PostMapping("login")
    public ResponseEntity<ResponseJwtToken> doLogin(@RequestBody @Valid DataAuthentication data){
        return ResponseEntity.ok(authenticationService.login(data));
    }

    @Operation(summary =  "Register user", method = "POST", description ="Method to register user",  responses = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody @Valid UserDtoPost registerDto){
        return new ResponseEntity<>(authenticationService.register(registerDto), HttpStatus.CREATED);
    }

    @Operation(summary =  "Replace user", method = "PUT", description ="Method to update user",  responses = {
            @ApiResponse(responseCode = "204", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @PutMapping("/replace")
    public ResponseEntity<Void> replace(@RequestBody @Valid UserDtoPut userDtoPut){
        authenticationService.replace(userDtoPut);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary =  "Delete user", method = "DELETE", description ="Method to delete user",  responses = {
            @ApiResponse(responseCode = "204", description = "successful operation", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        authenticationService.deleteRegister(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
