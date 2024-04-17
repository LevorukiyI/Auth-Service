package com.ascory.authservice.models;

import com.ascory.authservice.requests.EmailPassRegisterRequestEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "email_pass_verification_token_entity")
public class EmailPassVerificationTokenEntity {
    @Id
    private String token;

    @OneToOne
    @JoinColumn(name = "register_request_id")
    private EmailPassRegisterRequestEntity registerRequestEntity;

    private Long principal;
}
