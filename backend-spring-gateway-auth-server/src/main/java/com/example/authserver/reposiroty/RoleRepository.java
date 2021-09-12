package com.example.authserver.reposiroty;

import com.example.authserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByUser_id(long id);
}
