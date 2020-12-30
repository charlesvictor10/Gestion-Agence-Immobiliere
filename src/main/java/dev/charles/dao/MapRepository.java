package dev.charles.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.charles.entities.Map;
import dev.charles.entities.Logement;

public interface MapRepository extends JpaRepository<Map, Long> {
	public List<Map> findByLogement(Logement l); 
}
