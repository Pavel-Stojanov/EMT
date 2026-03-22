package mk.ukim.finki.library_api.service.application.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.domain.Country;
import mk.ukim.finki.library_api.model.dto.DisplayCountryDto;
import mk.ukim.finki.library_api.service.application.CountryApplicationService;
import mk.ukim.finki.library_api.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    @Override
    public List<DisplayCountryDto> getAllCountries() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public DisplayCountryDto getCountryById(Long id) {
        Country country = countryService.findById(id);
        return DisplayCountryDto.from(country);
    }
}
