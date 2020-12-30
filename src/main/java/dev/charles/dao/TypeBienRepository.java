package dev.charles.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.charles.entities.TypeBien;

public interface TypeBienRepository extends JpaRepository<TypeBien, Long> {
	@Query("select t from TypeBien t where t.designation like :x")
	public Page<TypeBien> chercher(@Param("x") String e, Pageable pageable);
}
