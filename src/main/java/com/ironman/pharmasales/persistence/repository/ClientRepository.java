package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query(value = "SELECT c FROM Client AS c" +
            " WHERE ( :#{#client.name} IS NULL OR UPPER(c.name) LIKE UPPER(CONCAT('%',:#{#client.name},'%')) )" +
            " AND ( :#{#client.lastName} IS NULL OR UPPER(c.lastName) LIKE UPPER(CONCAT('%',:#{#client.lastName},'%')) )" +
            " AND ( :#{#client.documentTypeId} IS NULL OR c.documentTypeId = :#{#client.documentTypeId} )" +
            " AND ( :#{#client.documentNumber} IS NULL OR c.documentNumber LIKE CONCAT('%',:#{#client.documentNumber},'%') )" +
            " AND ( :#{#client.state} IS NULL OR UPPER(c.state) = UPPER(:#{#client.state}) )"
    )
    Page<Client> paginationFilter(Pageable pageable, @Param("client") Client client);

    @Query( value = "SELECT c FROM Client AS c" +
            " WHERE CONCAT(UPPER(c.name), ' ', UPPER(c.lastName), ' ', UPPER(c.documentNumber)) LIKE UPPER(CONCAT('%',:searchText,'%')) " +
            " AND c.state = 'A'"
    )
    List<Client> search(@Param("searchText") String searchText);
}
