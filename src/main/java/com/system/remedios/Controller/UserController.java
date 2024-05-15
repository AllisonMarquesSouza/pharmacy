package com.system.remedios.Controller;

import com.system.remedios.domain.Usuario;
import com.system.remedios.requests.UserPostRequestBody;
import com.system.remedios.requests.UserPutRequestBody;
import com.system.remedios.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
@Tag(name = "open-api")
public class UserController {
    private final UserService userService;

    @Operation(summary =  "List all users", method = "POST",
            description ="List all users",
            tags = {"User"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),})
    @GetMapping("/listAll")
    public ResponseEntity<List<Usuario>> listAll(){
        return ResponseEntity.ok(userService.listAll());
    }

    @Operation(summary = "Search the user for username",
            description ="Search for details of user for username",
            tags = {"User"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),})
    @GetMapping("/findByUsername/{username}")
    @Transactional
    public ResponseEntity<UserDetails> findByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @Operation(summary = "Search the user for id",
            description ="Search for details of user for id",
            tags = {"User"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200",content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class),
                            examples = @ExampleObject())),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),})
    @GetMapping("/findById/{id}")
    @Transactional
    public ResponseEntity<UserDetails> findById(@PathVariable long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Create login to user",
            description ="Create login to user",
            tags = {"User"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @PostMapping("/createUser")
    @Transactional
    public ResponseEntity<Usuario> makeRegister(@RequestBody @Valid UserPostRequestBody userPostRequestBody){
        return new ResponseEntity<>(userService.makeRegister(userPostRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Replace the data of user",
            description ="Replace the data of user",
            tags = {"User"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content(
                            mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @PutMapping("/replace")
    @Transactional
    public ResponseEntity<Void> replace(@RequestBody @Valid UserPutRequestBody userPutRequestBody){
        userService.replace(userPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Remove the user ",
            description ="Remove the user",
            tags = {"User"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "204",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "400",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "401",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "404",content = @Content),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "500",content = @Content),
            })
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteFull(@PathVariable long id){
        userService.deleteRegister(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
