package com.pwang.dockerviewservices.constant;

import org.springframework.stereotype.Component;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/19/2023
 */

@Component
public class DockerCommandTemplate {

    public final static String CREATE_DOCKER_CONTAINER = "docker run --env ${env} --name ${containerName} --detach ${imageName}";

    public final static String RENAME_DOCKER_CONTAINER = "docker rename ${currentName} ${newName}";

    public final static String DELETE_DOCKER_CONTAINER = "docker rm -f ${containerName}";

    public final static String LIST_DOCKER_CONTAINERS = "docker ps -all";

    public final static String LIST_RUNNING_CONTAINERS = "docker ps";

    public final static String LIST_IMAGES = "docker images";

    public final static String STOP_CONTAINER = "docker stop ${containerName}";

    public final static String RESTART_CONTAINER = "docker restart ${containerName}";

    public final static String GET_VERSION = "docker --version";
}
