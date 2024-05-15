package com.system.remedios.Repository;

import com.system.remedios.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Usuario, Long> {
     Usuario findByUsername(String username);

}
