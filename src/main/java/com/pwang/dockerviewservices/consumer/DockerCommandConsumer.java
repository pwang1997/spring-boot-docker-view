package com.pwang.dockerviewservices.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */

@Component
@Slf4j
public class DockerCommandConsumer {
    String homeDirectory = System.getProperty("user.home");

    public String run(String command) throws IOException, InterruptedException, RuntimeException {
        Process process = Runtime.getRuntime()
                .exec(String.format(command, homeDirectory));
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null, previous = null;
        while ((line = br.readLine()) != null) {
            if (!line.equals(previous)) {
                previous = line;
                out.append(line).append('\n');
                System.out.println(line);
            }
        }
        br.close();

        log.info(command);
        log.info(out.toString());

        //Check result
        if (process.waitFor() == 0 || !out.toString().isEmpty()) {
            log.info(String.format("Command Success: [%s]", command));
        } else {
            log.error(String.format("Command: [%s] didn't execute", command));
            throw new RuntimeException(String.format("Command: [%s] didn't execute", command));
        }

        return out.toString();
    }
}
