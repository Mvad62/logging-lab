package ru.liga;

import lombok.extern.slf4j.Slf4j;
import ru.liga.controller.ConsoleController;
import ru.liga.repository.CountryRepository;
import ru.liga.service.CountryImportService;
import ru.liga.util.CsvParser;
import ru.liga.util.CsvReader;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Стартуем приложение...");
        Main.start();
    }

    private static void start() {
        ConsoleController consoleController = new ConsoleController(
                new CountryImportService(
                        new CsvParser(new CsvReader()),
                        new CountryRepository()));
        consoleController.listen();
    }

}