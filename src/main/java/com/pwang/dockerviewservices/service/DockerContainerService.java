package com.pwang.dockerviewservices.service;

import com.pwang.dockerviewservices.DTO.DockerContainerDTO;
import com.pwang.dockerviewservices.model.DockerContainer;

import java.util.UUID;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */
public interface DockerContainerService {

    Iterable<DockerContainer> list();

    boolean create(DockerContainerDTO dockerContainerDTO);

    boolean update(DockerContainerDTO dockerContainerDTO);

    boolean deleteByUUID(String uuid);
}
