package com.ascory.authservice.requests;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "email_pass_register_requests")
public class EmailPassRegisterRequestEntity {
    @Id
    private String email;

    @Column
    private String password;

    @Column
    private String confirmPassword;
}
