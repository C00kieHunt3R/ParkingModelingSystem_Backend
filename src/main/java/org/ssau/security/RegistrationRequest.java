package org.ssau.security;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String role;
}
