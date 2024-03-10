package com.technonext.transport.model.user;


import com.technonext.transport.generic.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "t_users")
public class User extends BaseEntity {

    @NotBlank
    @Column(unique = true)
    private String username;

    @Column(unique = true)
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String fullName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
}
