package com.epam.jmp.spring.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;

import com.epam.jmp.spring.exception.ApplicationException;
import com.epam.jmp.spring.model.Base;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@RequiredArgsConstructor
public class InMemoryStorage {
    private final ObjectMapper mapper;

    private final ConcurrentHashMap<String, List<? extends Base>> storage = new ConcurrentHashMap<>();

    public void loadData(Class<? extends Base> clazz, String path) throws IOException {
        log.info("Received path for test data: {}", path);
        File resource = new ClassPathResource(path).getFile();
        List<String> lines = Files.readAllLines(resource.toPath());
        storage.put(clazz.getSimpleName(), lines.stream().map(line -> {
            try {
                return (mapper.readValue(line, clazz));
            } catch (JsonProcessingException e) {
                throw new ApplicationException("Error while filling data");
            }
        }).collect(Collectors.toList()));
        log.info("Data was read successfully!");
    }

    public List<? extends Base> getData(Class<? extends Base> clazz) {
        return storage.get(clazz.getSimpleName());
    }
}
