package mk.ukim.finki.library_api.service.domain;

import mk.ukim.finki.library_api.model.domain.Author;

public interface AuthorService {
    Author findById(Long id);
}
