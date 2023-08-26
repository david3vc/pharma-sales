package com.iroman.pharmasales.persistence.repository;

import com.iroman.pharmasales.persistence.entity.DocumentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentTypeRepository extends CrudRepository<DocumentType, Long>, PagingAndSortingRepository<DocumentType, Long> {
    List<DocumentType> findByState(String state);
    @Query(value = "SELECT d FROM DocumentType d" + " WHERE d.state = :state")
    List<DocumentType> searchByState(@Param("state") String state);
}
