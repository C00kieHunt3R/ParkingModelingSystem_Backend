package org.ssau.security;

import lombok.Data;

@Data
public class JwtResponse {

    private final String type = "Bearer";
    private String token;
    private String username;
    private String userRole;

    public JwtResponse(String username, String userRole, String token) {
        this.username = username;
        this.userRole = userRole;
        this.token = String.format("%s %s", this.type, token);
    }
}
