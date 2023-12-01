package com.malek.app.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.malek.app.rest.Model.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

}
