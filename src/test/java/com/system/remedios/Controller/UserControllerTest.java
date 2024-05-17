package com.system.remedios.Controller;

import com.system.remedios.domain.Usuario;
import com.system.remedios.requests.*;
import com.system.remedios.service.UserService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
@ExtendWith(SpringExtension.class)
@Log4j2
class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userService.listAll())
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(UserCreator.createUserToBeSaved());

        BDDMockito.when(userService.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(UserCreator.createUserToBeSaved());

        BDDMockito.when(userService.save(ArgumentMatchers.any(UserPostRequestBody.class)))
                .thenReturn(UserCreator.createUserToBeSaved());

        BDDMockito.doNothing().when(userService).replace(ArgumentMatchers.any(UserPutRequestBody.class));

        BDDMockito.doNothing().when(userService).deleteRegister(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Return List of User when is successful")
    void list_ReturnsListOfUser_WhenSuccessful() {
        String expectedName = UserCreator.createValidUser().getUsername();

        List<Usuario> userResponse = userController.listAll().getBody();

        Assertions.assertThat(userResponse).isNotNull();

        Assertions.assertThat(userResponse).isNotEmpty().hasSize(1);

        Assertions.assertThat(userResponse.get(0).getUsername()).isEqualTo(expectedName);

    }
    @Test
    @DisplayName("FindById return user when is successful")
    void findById_ReturnUser_WhenSuccessful() {
        Long expectedId = UserCreator.createUserToBeSaved().getId();

        Usuario userResponse = (Usuario) userController.findById(1L).getBody();

        Assertions.assertThat(userResponse).isNotNull();

        Assertions.assertThat(userResponse.getId()).isNotNull();

        Assertions.assertThat(userResponse.getId()).isEqualTo(expectedId);

    }
    @Test
    @DisplayName("FindByName return user when is successful")
    void findByName_ReturnUser_WhenSuccessful() {
        String expectedUsername = UserCreator.createUserToBeSaved().getUsername();

        Usuario userResponse = (Usuario) userController.findByUsername("").getBody();

        Assertions.assertThat(userResponse).isNotNull();

        Assertions.assertThat(userResponse.getUsername()).isNotNull();

        Assertions.assertThat(userResponse.getUsername()).isEqualTo(expectedUsername);

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
    @DisplayName("Replace User when is successful")
    void replaceUser_ReturnWhenSuccessful() {

        ResponseEntity<Void> entity = userController.replace(UserPutRequestCreator.userPutRequestCreator());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat((entity.getStatusCode())).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThatCode(() -> userController.replace(UserPutRequestCreator.userPutRequestCreator()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("DeleteById User when is successful")
    void deleteById_WhenSuccessful() {
        ResponseEntity<Void> delete = userController.deleteFull(1);

        Assertions.assertThat(delete).isNotNull();
        Assertions.assertThat((delete.getStatusCode())).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThatCode(() -> userController.deleteFull(1))
                .doesNotThrowAnyException();

    }



}