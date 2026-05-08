package mk.ukim.finki.library_api.service.application;

import mk.ukim.finki.library_api.model.dto.CreateCountryDto;
import mk.ukim.finki.library_api.model.dto.DisplayCountryDto;

import java.util.List;

public interface CountryApplicationService {
    List<DisplayCountryDto> getAllCountries();

    DisplayCountryDto getCountryById(Long id);

    DisplayCountryDto createCountry(CreateCountryDto countryDto);

    DisplayCountryDto updateCountry(Long id, CreateCountryDto countryDto);

    void deleteCountry(Long id);
}
