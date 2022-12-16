package org.ssau.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssau.model.UserAccount;
import org.ssau.repository.UserAccountRepository;
import org.ssau.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserDetailsImpl.build(
                (UserAccount) userAccountRepository.findUserAccountByUsername(username)
                        .orElseThrow(()
                                -> new UsernameNotFoundException("Пользователя с таким именем не существует: " + username))
        );
    }
}
