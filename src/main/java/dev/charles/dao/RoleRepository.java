package dev.charles.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.charles.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
