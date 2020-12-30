package dev.charles.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.charles.entities.Logement;

public interface LogementRepository extends JpaRepository<Logement, Long> {
	@Query("select l from Logement l where l.referenceLogement like :x")
	public Page<Logement> chercher(@Param("x") String e, Pageable pageable);
	public List<Logement> findByDateEnregistrement(Date dateEnregistrement, Pageable pageable);
	@Query("select l from Logement l where l.ville.nomVille = :x or l.typeBien.designation = :y or l.nombrePieceLogement = :z or l.etatLogement = :v or l.superficieLogement = :u or l.prixLogement = :t")
	public Page<Logement> recherche(@Param("x") String a, @Param("y") String b, @Param("v") String d, @Param("z") int c, @Param("u") String e, @Param("t") Double i, Pageable pageable);
}
