package mk.ukim.finki.library_api.service.domain;

import mk.ukim.finki.library_api.model.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void delete(Book book);
}
