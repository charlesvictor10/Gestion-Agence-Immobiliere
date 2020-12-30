package dev.charles.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.charles.entities.Temoignage;

public interface TemoignageRepository extends JpaRepository<Temoignage, Long> {

}
