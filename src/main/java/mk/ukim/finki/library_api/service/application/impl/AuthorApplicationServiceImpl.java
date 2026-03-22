package mk.ukim.finki.library_api.service.application.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.domain.Author;
import mk.ukim.finki.library_api.model.domain.Country;
import mk.ukim.finki.library_api.model.dto.CreateAuthorDto;
import mk.ukim.finki.library_api.model.dto.DisplayAuthorDto;
import mk.ukim.finki.library_api.service.application.AuthorApplicationService;
import mk.ukim.finki.library_api.service.domain.AuthorService;
import mk.ukim.finki.library_api.service.domain.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;

    @Override
    public Page<DisplayAuthorDto> getAllAuthors(Pageable pageable) {
        return authorService.findAll(pageable).map(DisplayAuthorDto::from);
    }

    @Override
    public DisplayAuthorDto getAuthorById(Long id) {
        return DisplayAuthorDto.from(authorService.findById(id));
    }

    @Override
    public DisplayAuthorDto createAuthor(CreateAuthorDto authorDto) {
        Country country = countryService.findById(authorDto.countryId());
        Author savedAuthor = authorService.save(authorDto.toAuthor(country));
        return DisplayAuthorDto.from(savedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorService.delete(id);
    }
}
