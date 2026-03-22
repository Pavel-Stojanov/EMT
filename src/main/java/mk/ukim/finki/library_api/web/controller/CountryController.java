package mk.ukim.finki.library_api.web.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.dto.DisplayCountryDto;
import mk.ukim.finki.library_api.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    @GetMapping
    public List<DisplayCountryDto> getAllCountries() {
        return countryApplicationService.getAllCountries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> getCountryById(@PathVariable Long id) {
        return ResponseEntity.ok(countryApplicationService.getCountryById(id));
    }


}
