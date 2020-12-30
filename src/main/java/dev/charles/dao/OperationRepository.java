package dev.charles.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.charles.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
