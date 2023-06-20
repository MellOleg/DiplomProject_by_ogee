package org.olegmell.repository;

import org.olegmell.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(
            value = "SELECT * FROM REQUEST r WHERE r.status_id <> 3 ORDER BY creation_date DESC",
            nativeQuery = true)
    List<Request> findAllActiveRequests();

    @Query(
            value = "SELECT * FROM REQUEST r WHERE r.status_id = 1 ORDER BY creation_date ASC",
            nativeQuery = true)
    List<Request> findAllPendingRequests();
}
