package mk.ukim.finki.library_api.service.domain.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.domain.Author;
import mk.ukim.finki.library_api.model.exception.AuthorNotFoundException;
import mk.ukim.finki.library_api.repository.AuthorRepository;
import mk.ukim.finki.library_api.service.domain.AuthorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }
}
