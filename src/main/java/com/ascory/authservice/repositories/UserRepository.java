package com.ascory.authservice.repositories;

import com.ascory.authservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByDiscordId(String discordId);
    User getUserByDiscordId(String discordId);
    User getUserById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
