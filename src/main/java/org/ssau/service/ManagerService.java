package org.ssau.service;

import org.ssau.model.UserAccount;

import java.util.List;

public interface ManagerService {
    List<UserAccount> getAll();
    UserAccount update(Long id, UserAccount userAccount);
    void delete(Long id);

    UserAccount create(UserAccount userAccount);
    UserAccount getById(Long id);

    UserAccount getByUsername(String username);
}
