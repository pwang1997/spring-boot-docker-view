package com.pwang.dockerviewservices.service.impl;

import com.pwang.dockerviewservices.DTO.DockerContainerDTO;
import com.pwang.dockerviewservices.mapper.DockerContainerMapper;
import com.pwang.dockerviewservices.model.DockerContainer;
import com.pwang.dockerviewservices.repository.DockerContainerRepository;
import com.pwang.dockerviewservices.service.DockerContainerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */

@Service
@AllArgsConstructor
@Transactional
public class DockerContainerServiceImpl implements DockerContainerService {

    private final DockerContainerRepository dockerContainerRepository;

    private final DockerContainerMapper mapper;

    @Override
    public Iterable<DockerContainer> list() {

        return dockerContainerRepository.findAll();
    }

    @Override
    public boolean create(DockerContainerDTO dockerContainerDTO) {

        DockerContainer container = DockerContainer.builder()
                .uuid(UUID.randomUUID().toString())
                .name(dockerContainerDTO.name())
                .ports(dockerContainerDTO.ports())
                .image(dockerContainerDTO.image())
                .createAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .build();

        DockerContainer dockerContainer = dockerContainerRepository.save(container);

        boolean success = dockerContainer.getId() != null;

        dockerContainerRepository.flush();

        return success;
    }

    @Override
    public boolean update(DockerContainerDTO dockerContainerDTO) {
        DockerContainer container = mapper.convertToInstance(dockerContainerDTO);

        return dockerContainerRepository
                .findByUUID(dockerContainerDTO.uuid())
                .map(experience -> {
                    dockerContainerRepository.saveAndFlush(container);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean deleteByUUID(String uuid) {
        return dockerContainerRepository
                .findByUUID(uuid)
                .map(dockerContainer -> {
                    dockerContainerRepository.deleteByUUID(uuid);
                    return true;
                })
                .orElse(false);
    }
}
