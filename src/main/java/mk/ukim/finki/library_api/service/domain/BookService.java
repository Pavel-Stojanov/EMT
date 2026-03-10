package mk.ukim.finki.library_api.service.domain;

import mk.ukim.finki.library_api.model.domain.Book;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void delete(Book book);

    List<Book> filter (Category category, State state, Long authorId);
}
