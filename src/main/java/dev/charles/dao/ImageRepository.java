package dev.charles.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.charles.entities.Image;
import dev.charles.entities.Logement;

public interface ImageRepository extends JpaRepository<Image, Long> {
	@Query("select i from Image i where i.nomImage like :x")
	public Page<Image> chercher(@Param("x") String e, Pageable pageable);
	public List<Image> findByLogement(Logement l); 
}
