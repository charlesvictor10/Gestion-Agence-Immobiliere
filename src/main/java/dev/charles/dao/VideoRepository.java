package dev.charles.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.charles.entities.Logement;
import dev.charles.entities.Video;

public interface VideoRepository extends JpaRepository<Video,    Long> {
	@Query("select v from Video v where v.nomVideo like :x")
	public Page<Video> chercher(@Param("x") String e, Pageable pageable);
	public List<Video> findByLogement(Logement l); 
}
