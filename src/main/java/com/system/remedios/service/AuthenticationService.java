package com.system.remedios.service;

import com.system.remedios.Repository.UserRepository;
import com.system.remedios.domain.User;
import com.system.remedios.dtos.DataAuthentication;
import com.system.remedios.dtos.ResponseJwtToken;
import com.system.remedios.dtos.UserDtoPost;
import com.system.remedios.dtos.UserDtoPut;
import com.system.remedios.exception.personalizedExceptions.BadRequestException;
import com.system.remedios.exception.personalizedExceptions.ResourceAlreadyExistsException;
import com.system.remedios.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ApplicationContext context;
    private  final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }



    public ResponseJwtToken login(DataAuthentication data){
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManager.class);
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return new ResponseJwtToken(token);
        } catch (Exception e) {
            throw new BadRequestException("Don't possible to make login , check your username and password");
        }
    }


    @Transactional
    public User register (UserDtoPost registerDto){
        if (this.userRepository.findByUsername(registerDto.getUsername()) != null){
            throw new ResourceAlreadyExistsException("Email or username, already exist!");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.getPassword());
        registerDto.setPassword(encryptedPassword);

        User newUser = new User(registerDto.getUsername(), registerDto.getPassword());
        return this.userRepository.save(newUser);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public void replace(UserDtoPut userDtoPut){
        User userFound = this.findById(userDtoPut.getId());

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDtoPut.getPassword());
        userDtoPut.setPassword(encryptedPassword);
        userDtoPut.setId(userFound.getId());

        User userToBeSaved = new User(userDtoPut.getId(), userDtoPut.getUsername(), userDtoPut.getPassword());
        userRepository.save(userToBeSaved);

    }

    @Transactional
    public void deleteRegister(Long id){
        userRepository.delete(this.findById(id));
    }

}
