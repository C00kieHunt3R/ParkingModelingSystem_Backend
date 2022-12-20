package org.ssau.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssau.model.UserAccount;
import org.ssau.model.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findUserAccountByUsername(String username);
    List<UserAccount> findUserAccountsByRole(UserRole userRole);
}
