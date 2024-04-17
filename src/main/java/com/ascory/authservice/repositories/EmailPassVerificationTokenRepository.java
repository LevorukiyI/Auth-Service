package com.ascory.authservice.repositories;

import com.ascory.authservice.models.EmailPassVerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailPassVerificationTokenRepository extends JpaRepository<EmailPassVerificationTokenEntity, Long> {
    Optional<EmailPassVerificationTokenEntity> findByToken(String token);
}
