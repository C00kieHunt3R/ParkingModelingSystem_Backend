package org.ssau.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssau.model.UserAccount;
import org.ssau.model.UserRole;
import org.ssau.repository.UserAccountRepository;
import org.ssau.service.ManagerService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    UserAccountRepository uar;

    @Override
    public List<UserAccount> getAll() {
        return uar.findUserAccountsByRole(UserRole.ROLE_MANAGER);
    }

    @Override
    public UserAccount update(Long id, UserAccount userAccount) {
        AtomicReference<UserAccount> userAccountRet = new AtomicReference<>();
        uar.findById(id).ifPresent(p -> {
            userAccount.setId(p.getId());
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
        userAccount.setRole(UserRole.ROLE_MANAGER);
        return uar.saveAndFlush(userAccount);
    }
    @Override
    public UserAccount getById(Long id) {
        AtomicReference<UserAccount> userAccountRet = new AtomicReference<>();
        uar.findById(id).ifPresent(userAccountRet::set);
        return userAccountRet.get();
    }
}
