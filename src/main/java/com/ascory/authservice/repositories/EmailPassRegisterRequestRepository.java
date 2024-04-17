package com.ascory.authservice.repositories;

import com.ascory.authservice.requests.EmailPassRegisterRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailPassRegisterRequestRepository extends JpaRepository<EmailPassRegisterRequestEntity, Long> {

}
