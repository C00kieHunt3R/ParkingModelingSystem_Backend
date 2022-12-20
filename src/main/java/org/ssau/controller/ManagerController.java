package org.ssau.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.ssau.model.UserAccount;
import org.ssau.service.ManagerService;
import java.util.List;
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
@CrossOrigin
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public List<UserAccount> getAll() {
        return managerService.getAll();
    }

    @GetMapping(path = "/get/{username}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public UserAccount getByUsername(@PathVariable("username") String username) {
        return managerService.getByUsername(username);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public UserAccount getById(@PathVariable("id") Long id) {
        return managerService.getById(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public UserAccount create(@RequestBody UserAccount userAccount) {
        return managerService.create(userAccount);
    }
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public UserAccount update(@PathVariable("id") Long id,
                          @RequestBody UserAccount userAccount) {
        return managerService.update(id, userAccount);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        managerService.delete(id);
        return ResponseEntity.ok().body(null);
    }
}
