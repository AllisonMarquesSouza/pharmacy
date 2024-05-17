package com.system.remedios.Repository;

import com.system.remedios.domain.Usuario;
import com.system.remedios.util.UserCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
@DataJpaTest
@ActiveProfiles("test")
@Log4j2
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void findByUsername() {
        Usuario userSaved = userRepository.save(UserCreator.createUserToBeSaved());

        Usuario allByUser = userRepository.findByUsername("theCrazy");

        Assertions.assertThat(allByUser.getId()).isNotNull();

        Assertions.assertThat(allByUser.getPassword()).isNotNull();

        Assertions.assertThat(allByUser).isNotNull();

        Assertions.assertThat(allByUser).isEqualTo(userSaved);

    }
}