package com.pwang.dockerviewservices.DTO;

import java.util.UUID;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */
public record CommandDTO(
        String command,
        UUID uuid
) {
}
