package com.ascory.authservice.repositories;

import com.ascory.authservice.models.JwtRefreshToken;
import com.ascory.authservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, Long> {
    void deleteIfExistsByUser(User user);
}
