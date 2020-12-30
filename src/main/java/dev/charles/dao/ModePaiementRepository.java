package dev.charles.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.charles.entities.ModePaiement;

public interface ModePaiementRepository extends JpaRepository<ModePaiement, Long> {

}
