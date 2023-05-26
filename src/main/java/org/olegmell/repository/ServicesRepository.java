package org.olegmell.repository;

import org.olegmell.domain.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ServicesRepository extends JpaRepository<Services, Integer> {

}