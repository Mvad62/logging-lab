package ru.liga.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import ru.liga.domain.Country;
import ru.liga.repository.CountryRepository;
import ru.liga.util.CsvParser;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CountryImportService {
    private final CsvParser csvParser;
    private final CountryRepository countryRepository;

    public void importCountries(String filePath) {
        try {
            MDC.put("fileName", filePath);
            log.info("Начало импортирования стран из {}", filePath);
            List<Country> countries = csvParser.parseCountriesFromFile(filePath);
            countryRepository.saveAll(countries);
            if(!countries.isEmpty()) {
                log.info("Успешно импортировано {} стран.", countries.size());
            }
            else {
                log.info("Ничего не импортировано из файла {}", filePath);
            }
        }
        finally {
            MDC.clear();
        }
    }
}
