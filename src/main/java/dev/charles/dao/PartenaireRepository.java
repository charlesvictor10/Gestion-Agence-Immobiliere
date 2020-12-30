package dev.charles.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.charles.entities.Partenaires;

public interface PartenaireRepository extends JpaRepository<Partenaires, Long> {
	@Query("select p from Partenaires p where p.nomPartenaire like :x")
	public Page<Partenaires> chercher(@Param("x") String e, Pageable pageable);
}
