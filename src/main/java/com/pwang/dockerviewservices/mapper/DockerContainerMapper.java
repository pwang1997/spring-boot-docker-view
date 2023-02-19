package com.pwang.dockerviewservices.mapper;

import com.pwang.dockerviewservices.DTO.DockerContainerDTO;
import com.pwang.dockerviewservices.model.DockerContainer;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */

@Service
public class DockerContainerMapper implements BaseMapper<DockerContainerDTO, DockerContainer> {

    @Override
    public DockerContainer convertToInstance(DockerContainerDTO object) {
         return DockerContainer.builder()
                .id(object.id())
                .uuid(object.uuid() == null ? UUID.randomUUID().toString() : object.uuid())
                .name(object.name())
                .ports(object.ports())
                .image(object.image())
                .startAt(object.startAt())
                .createAt(object.createAt() == null ? System.currentTimeMillis() : object.createAt())
                .updatedAt(System.currentTimeMillis())
                .build();
    }
}
