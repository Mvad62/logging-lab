package ru.liga.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CsvReader {
    public List<String> readAllLines(String csvPath) {
        try {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvPath);

        if (inputStream == null) {
            log.error("Файл {} не найден в resources", csvPath);
            return Collections.emptyList();
        }
            return Files.readAllLines(new File(getClass()
                    .getClassLoader()
                    .getResource(csvPath)
                    .toURI())
                    .toPath());
        }
        catch (Exception e) {
            log.error("Неизвестная ошибка {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
