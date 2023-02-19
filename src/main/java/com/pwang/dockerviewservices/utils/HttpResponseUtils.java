package com.pwang.dockerviewservices.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/19/2023
 */

@Component
public class HttpResponseUtils {

    public static ResponseEntity<String> clientMessage(String message, HttpStatus status) {
        switch (status) {
            case BAD_REQUEST -> {
                return ResponseEntity.badRequest().body(message);
            }
            default -> {
                return ResponseEntity.ok(message);
            }
        }
    }

}
