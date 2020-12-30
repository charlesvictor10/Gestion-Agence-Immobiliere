package dev.charles.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.charles.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
	@Query("select u from Utilisateur u where u.prenomUtilisateur like :x")
	public Page<Utilisateur> chercher(@Param("x") String e, Pageable pageable);
}