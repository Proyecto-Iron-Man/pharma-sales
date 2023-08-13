package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query( value = "SELECT c FROM Client AS c" +
            " WHERE CONCAT(UPPER(c.name), ' ', UPPER(c.lastName), ' ', UPPER(c.documentNumber)) LIKE UPPER(CONCAT('%',:searchText,'%')) " +
            " AND c.state = 'A'"
    )
    List<Client> search(@Param("searchText") String searchText);
}
