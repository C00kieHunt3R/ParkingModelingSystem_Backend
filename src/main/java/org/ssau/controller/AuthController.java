package org.ssau.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.ssau.model.UserAccount;
import org.ssau.model.UserRole;
import org.ssau.repository.UserAccountRepository;
import org.ssau.security.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public JwtResponse authenticate(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String userRole = userDetails.getAuthorities().toString();

        return new JwtResponse(
                userDetails.getUsername(),
                userRole.replace("[", "").replace("]", ""),
                jwt);
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody RegistrationRequest registrationRequest) {
        try {
            UserAccount user = new UserAccount();
            user.setFio(registrationRequest.getFio());
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            String requestedRole = registrationRequest.getRole();
            UserRole role;
            if (requestedRole != null) {
                role = requestedRole
                        .equalsIgnoreCase("ROLE_ADMINISTRATOR") ? UserRole.ROLE_ADMINISTRATOR : UserRole.ROLE_MANAGER;
            } else {
                role = UserRole.ROLE_MANAGER;
            }
            user.setRole(role);
            return userAccountRepository.save(user);
        } catch (Exception e) {
            return null;
        }

    }
}
