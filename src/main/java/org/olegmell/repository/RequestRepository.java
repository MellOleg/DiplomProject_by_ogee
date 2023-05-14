package org.olegmell.repository;

import org.olegmell.domain.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findByTag(String tag);
}
