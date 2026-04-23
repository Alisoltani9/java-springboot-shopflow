package soltani.code.shopflow.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import soltani.code.shopflow.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
