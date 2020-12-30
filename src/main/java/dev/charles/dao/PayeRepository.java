package dev.charles.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.charles.entities.Paye;

public interface PayeRepository extends JpaRepository<Paye, Long> {

}
