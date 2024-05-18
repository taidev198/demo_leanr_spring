package com.example.demo.user;

import com.example.demo.repository.RunRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

//that component be said to spring that awared of
@Component
public class RunJsonLoader implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(RunJsonLoader.class.getName());

    private final RunRepository runRepository;

    private ObjectMapper objectMapper;
    public RunJsonLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (runRepository.countRuns() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")){
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                runRepository.saveAll(allRuns.runs());
                logger.info(allRuns.runs().size() + " runs loaded");
            } catch (IOException ioException) {
                throw new RuntimeException("failed to load runs.json", ioException);
            }
        } else {
            logger.info("not loading runs from JSON data because the collection contains data");
        }
    }
}
