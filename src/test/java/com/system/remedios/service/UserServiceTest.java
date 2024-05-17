package com.system.remedios.service;

import com.system.remedios.Repository.UserRepository;
import com.system.remedios.domain.Usuario;
import com.system.remedios.requests.UserPostRequestCreator;
import com.system.remedios.util.UserCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@Log4j2
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userRepository.findAll())
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(UserCreator.createUserToBeSaved()));

        BDDMockito.when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(UserCreator.createUserToBeSaved());


        BDDMockito.when(userRepository.save(ArgumentMatchers.any(Usuario.class)))
                .thenReturn(UserCreator.createUserToBeSaved());


        BDDMockito.doNothing().when(userRepository).deleteById(ArgumentMatchers.anyLong());
    }
    @Test
    @DisplayName("ListAll returns User when is successful")
    void list_ReturnsListOfUser_WhenSuccessful() {
        Usuario validUsuario = UserCreator.createValidUser();

        List<Usuario> userResponse = userService.listAll();

        Assertions.assertThat(userResponse).isNotNull();

        Assertions.assertThat(userResponse).isNotEmpty().hasSize(1);

        Assertions.assertThat(userResponse.get(0).getUsername()).isEqualTo(validUsuario.getUsername());

    }

    @Test
    @DisplayName("findById return User when is successful")
    void findById_ReturnUser_WhenSuccessful() {
        Long expectedId = UserCreator.createUserToBeSaved().getId();

        Usuario byId = (Usuario) userService.findById(1L);

        Assertions.assertThat(byId.getId()).isNotNull().isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findByName return User when is successful")
    void findByName_ReturnUser_WhenSuccessful() {
        String expectedUsername = UserCreator.createUserToBeSaved().getUsername();

        Usuario byUsername = (Usuario) userService.findByUsername("");

        Assertions.assertThat(byUsername.getUsername()).isNotNull().isEqualTo(expectedUsername);

    }

    @Test
    @DisplayName("Save User return when is successful")
    void SaveUser_ReturnWhenSuccessful() {
        Usuario expectedUser = UserCreator.createUserToBeSaved();
        Usuario save = userService.save(UserPostRequestCreator.userPostRequestCreator());

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save).isEqualTo(expectedUser);

    }

    @Test
    @DisplayName("DeleteById User when is successful")
    void deleteById_WhenSuccessful() {
        Assertions.assertThatCode(() -> userService.deleteRegister(1L))
                .doesNotThrowAnyException();

    }

}