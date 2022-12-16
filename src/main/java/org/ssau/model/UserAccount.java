package org.ssau.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "username")
})
@Data
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "The FIO field is required")
    @Column(name = "fio", nullable = false)
    private String fio;

    @NotBlank(message = "The 'login' field is required")
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank(message = "The 'password' field is required")
    @Column(name = "password", nullable = false)
    //@JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;
}
