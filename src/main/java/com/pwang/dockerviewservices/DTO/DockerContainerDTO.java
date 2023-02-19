package com.pwang.dockerviewservices.DTO;

import java.util.List;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */
public record DockerContainerDTO(
Long id,
String uuid,
String name,
String image,
List<String> ports,
Long startAt,
Long createAt
) {

}
