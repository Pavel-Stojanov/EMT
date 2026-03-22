package mk.ukim.finki.library_api.service.domain;

import mk.ukim.finki.library_api.model.domain.Book;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import mk.ukim.finki.library_api.model.projections.BookExpandedProjection;
import mk.ukim.finki.library_api.model.projections.BookShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAll(Pageable pageable);

    Book findById(Long id);

    Book save(Book book);

    void delete(Book book);

    Page<Book> filter(Category category, State state, Long authorId, Boolean hasAvailable, Pageable pageable);

    Page<BookShortProjection> findAllShortProjections(Pageable pageable);

    Page<BookExpandedProjection> findAllExpandedProjections(Pageable pageable);
}
