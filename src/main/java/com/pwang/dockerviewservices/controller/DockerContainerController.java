package com.pwang.dockerviewservices.controller;

import com.pwang.dockerviewservices.DTO.CommandDTO;
import com.pwang.dockerviewservices.DTO.DockerContainerDTO;
import com.pwang.dockerviewservices.constant.DockerCommandTemplate;
import com.pwang.dockerviewservices.consumer.DockerCommandConsumer;
import com.pwang.dockerviewservices.model.DockerContainer;
import com.pwang.dockerviewservices.service.DockerContainerService;
import com.pwang.dockerviewservices.utils.HttpResponseUtils;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/container")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DockerContainerController {

    private final DockerContainerService dockerContainerService;

    private final DockerCommandConsumer dockerCommandConsumer;

    @GetMapping("/getVersion")
    public @ResponseBody ResponseEntity<String> getVersion() {
        log.info("GET DOCKER VERSION");
        String message;
        HttpStatus status = HttpStatus.OK;
        try {
            message = dockerCommandConsumer.run(DockerCommandTemplate.GET_VERSION);
        } catch (Exception e) {
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return HttpResponseUtils.clientMessage(message, status);
    }
    @GetMapping("/list")
    public @ResponseBody Iterable<DockerContainer> list() {
        return dockerContainerService.list();
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<String> create(@NotNull @RequestBody DockerContainerDTO dockerContainerDTO) {
        log.info(dockerContainerDTO.toString());
        String message;
        HttpStatus status = HttpStatus.OK;

        try {
            String command = new StringSubstitutor(Map.of("containerName", dockerContainerDTO.name()))
                    .replace(DockerCommandTemplate.DELETE_DOCKER_CONTAINER);
            message = dockerCommandConsumer.run(command);
            boolean success = dockerContainerService.create(dockerContainerDTO);
        } catch (Exception e) {
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return HttpResponseUtils.clientMessage(message, status);
    }

    @PutMapping("update")
    public @ResponseBody ResponseEntity<String> update(@NotNull @RequestBody DockerContainerDTO dockerContainerDTO) {
        boolean success = dockerContainerService.update(dockerContainerDTO);
        return success ? ResponseEntity.ok("SUCCESS") :
                ResponseEntity.badRequest().body("Failed");
    }

    @DeleteMapping("delete")
    public @ResponseBody ResponseEntity<String> delete(@NotNull @RequestBody DockerContainerDTO dockerContainerDTO) {
        log.info(String.valueOf(dockerContainerDTO));
        String message;
        HttpStatus status = HttpStatus.OK;
        try {
            String command = new StringSubstitutor(Map.of("containerName", dockerContainerDTO.name()))
                    .replace(DockerCommandTemplate.DELETE_DOCKER_CONTAINER);
            message = dockerCommandConsumer.run(command);
            dockerContainerService.deleteByUUID(dockerContainerDTO.uuid());
        } catch (Exception e) {
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return HttpResponseUtils.clientMessage(message, status);
    }

    @PostMapping("/invoke")
    public @ResponseBody ResponseEntity<String> invoke(@NotNull @RequestBody CommandDTO commandDTO) {
        String message;
        HttpStatus status = HttpStatus.OK;

        try {
            message = dockerCommandConsumer.run(commandDTO.command());
        } catch (Exception e) {
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        return HttpResponseUtils.clientMessage(message, status);
    }
}
