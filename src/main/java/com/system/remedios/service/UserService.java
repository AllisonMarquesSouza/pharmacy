package com.system.remedios.service;

import com.system.remedios.Mapper.UserMapper;
import com.system.remedios.Repository.UserRepository;
import com.system.remedios.domain.Usuario;
import com.system.remedios.requests.UserPostRequestBody;
import com.system.remedios.requests.UserPutRequestBody;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;

    public List<Usuario> listAll(){
        return userRepository.findAll();
    }
    public UserDetails findByUsername(String username){
        Usuario byUsername = userRepository.findByUsername(username);
        if(byUsername == null){
            throw new EntityNotFoundException();
        }
        return byUsername;
    }
    public UserDetails findById(Long id){
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public void replace(UserPutRequestBody userPutRequestBody){
        Usuario userSaved = (Usuario) findById(userPutRequestBody.getId());

        String encryptedPassword = PasswordCryptService.encryptPassword(userPutRequestBody.getPassword());
        userPutRequestBody.setPassword(encryptedPassword);
        userPutRequestBody.setId(userSaved.getId());

        Usuario user = UserMapper.INSTANCE.toUsuario(userPutRequestBody);
        userRepository.save(user);

    }
    public Usuario save(UserPostRequestBody userPost) {
        Usuario user = UserMapper.INSTANCE.toUsuario(userPost);
        String encryptedPassword = PasswordCryptService.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public void deleteRegister(Long id){
        userRepository.deleteById(id);
    }


}
