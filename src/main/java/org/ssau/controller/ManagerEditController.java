package org.ssau.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.ssau.model.UserAccount;
import org.ssau.service.ManagerService;
import java.util.List;
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
@CrossOrigin
public class ManagerEditController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    //@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public List<UserAccount> getAll() {
        return managerService.getAll();
    }

    @GetMapping(path = "/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public UserAccount getById(@PathVariable("id") Long id) {
        return managerService.getById(id);
    }
    @PostMapping
    //@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public UserAccount create(@RequestBody UserAccount userAccount) {
        return managerService.create(userAccount);
    }
    @PutMapping(path = "/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public UserAccount update(@PathVariable("id") Long id,
                          @RequestBody UserAccount userAccount) {
        return managerService.update(id, userAccount);
    }

    @DeleteMapping(path = "/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public void delete(@PathVariable Long id) {
        managerService.delete(id);
    }
}
