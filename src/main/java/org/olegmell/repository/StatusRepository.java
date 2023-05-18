package org.olegmell.repository;

import org.olegmell.domain.Statuses;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Statuses,Integer> {
}
