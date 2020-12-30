package dev.charles.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.charles.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	@Query("select c from Contact c where c.message like :x")
	public Page<Contact> chercher(@Param("x") String e, Pageable pageable);
}
