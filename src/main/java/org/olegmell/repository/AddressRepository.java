package org.olegmell.repository;

import org.olegmell.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    @Query(
            value = "SELECT * FROM ADDRESS a WHERE a.is_deleted = 'false' ORDER BY id",
            nativeQuery = true)
    List<Address> findAllAddress();
}
