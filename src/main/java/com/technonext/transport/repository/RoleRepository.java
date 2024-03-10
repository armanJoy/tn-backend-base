package com.technonext.transport.repository;

import com.technonext.transport.generic.repository.AbstractRepository;
import com.technonext.transport.model.user.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends AbstractRepository<Role> {

    Optional<Role> findByNameIgnoreCase(String name);
}
