package org.olegmell.repository;

import org.olegmell.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findFirstByName (String name);

}
