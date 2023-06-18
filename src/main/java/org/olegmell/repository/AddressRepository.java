package org.olegmell.repository;

import org.olegmell.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    @Query(
            value = "SELECT * FROM ADDRESS a WHERE a.is_deleted = 'false' ORDER BY id",
            nativeQuery = true)
    List<Address> findAllAddress();

    @Query(
            value = "SELECT * FROM ADDRESS a WHERE LOWER (a.address) LIKE %?1% AND a.is_deleted = 'false' ORDER BY id",
            nativeQuery = true)
    List<Address> search(String keyword);
}
