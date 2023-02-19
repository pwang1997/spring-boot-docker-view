package com.pwang.dockerviewservices.repository;

import com.pwang.dockerviewservices.model.DockerContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */

@Repository
public interface DockerContainerRepository extends JpaRepository<DockerContainer, Long> {

    @Modifying
    @Query("DELETE FROM DockerContainer dc WHERE dc.uuid = ?1 ")
    void deleteByUUID(String uuid);


    @Query(value = "SELECT dc FROM DockerContainer dc WHERE dc.uuid = ?1")
    Optional<DockerContainer> findByUUID(String uuid);
}
