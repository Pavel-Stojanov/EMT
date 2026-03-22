package mk.ukim.finki.library_api.service.application;

import mk.ukim.finki.library_api.model.dto.DisplayCountryDto;

import java.util.List;

public interface CountryApplicationService {
    List<DisplayCountryDto> getAllCountries();

    DisplayCountryDto getCountryById(Long id);
}
