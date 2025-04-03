package com.rocha.guerrero.testgenuine.repository;

import com.rocha.guerrero.testgenuine.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository  extends JpaRepository<Client, Long> {

    // Metodo para buscar por algun campo usando 'LIKE' y paginación
    @Query("SELECT s FROM Client s WHERE LOWER(s.data) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Client> findByFieldContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}
