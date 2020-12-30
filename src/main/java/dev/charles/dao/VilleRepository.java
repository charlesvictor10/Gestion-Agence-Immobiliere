package dev.charles.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.charles.entities.Ville;

public interface VilleRepository extends JpaRepository<Ville, Long> {
	@Query("select v from Ville v where v.nomVille like :x")
	public Page<Ville> chercher(@Param("x") String e, Pageable pageable);
}
