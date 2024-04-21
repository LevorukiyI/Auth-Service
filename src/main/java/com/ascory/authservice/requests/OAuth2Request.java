package com.ascory.authservice.requests;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuth2Request {
    private String code;
}
