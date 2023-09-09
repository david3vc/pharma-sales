package com.iroman.pharmasales.persistence.repository;

import com.iroman.pharmasales.persistence.entity.Client;
import com.iroman.pharmasales.persistence.entity.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    @Query(value = "SELECT c FROM Client c" +
            " WHERE CONCAT(UPPER(c.name), ' ', UPPER(c.lastName), ' ', UPPER(c.documentNumber)) LIKE UPPER(CONCAT('%',:searchText,'%')) " +
            " AND c.state = 'A'")
    List<Client> search(@Param("searchText") String searchText);
    @Query(value = "SELECT c FROM Client c" +
            " WHERE (:#{#client.name} IS NULL OR UPPER(c.name) LIKE UPPER(CONCAT('%',:#{#client.name},'%')))" +
            " AND (:#{#client.lastName} IS NULL OR UPPER(c.lastName) LIKE UPPER(CONCAT('%',:#{#client.lastName},'%')))" +
            " AND (:#{#client.documentNumber} IS NULL OR UPPER(c.documentNumber) LIKE UPPER(CONCAT('%',:#{#client.documentNumber},'%')))" +
            " AND (:#{#client.phoneNumber} IS NULL OR UPPER(c.phoneNumber) LIKE UPPER(CONCAT('%',:#{#client.phoneNumber},'%')))" +
            " AND (:#{#client.address} IS NULL OR UPPER(c.address) LIKE UPPER(CONCAT('%',:#{#client.address},'%')))" +
            " AND (:#{#client.documentTypeId} IS NULL OR c.documentTypeId = :#{#client.documentTypeId})" +
            " AND (:#{#client.state} IS NULL OR UPPER(c.state) = UPPER(:#{#client.state}))"
    )
    Page<Client> paginationFilter(Pageable pageable, @Param("client") Client client);
}
