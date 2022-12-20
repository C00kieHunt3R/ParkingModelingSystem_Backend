package org.ssau.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.ssau.model.UserAccount;
import org.ssau.model.UserRole;
import org.ssau.repository.UserAccountRepository;
import org.ssau.service.ManagerService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    UserAccountRepository uar;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserAccount> getAll() {
        return uar.findUserAccountsByRole(UserRole.ROLE_MANAGER);
    }

    @Override
    public UserAccount update(Long id, UserAccount userAccount) {
        AtomicReference<UserAccount> userAccountRet = new AtomicReference<>();
        uar.findById(id).ifPresent(p -> {
            userAccount.setId(p.getId());
            userAccount.setRole(UserRole.ROLE_MANAGER);
            if (userAccount.getPassword().equals("")) {
                userAccount.setPassword(p.getPassword());
            } else {
                userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
            }
            userAccountRet.set(uar.saveAndFlush(userAccount));
        });
        return userAccountRet.get();
    }

    @Override
    public void delete(Long id) {
        uar.deleteById(id);
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        //userAccount.setId(null);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setRole(UserRole.ROLE_MANAGER);
        return uar.saveAndFlush(userAccount);
    }
    @Override
    public UserAccount getById(Long id) {
        AtomicReference<UserAccount> userAccountRet = new AtomicReference<>();
        uar.findById(id).ifPresent(userAccountRet::set);
        return userAccountRet.get();
    }
    @Override
    public UserAccount getByUsername(String username) {
        return uar.findUserAccountByUsername(username).orElse(null);
    }
}
