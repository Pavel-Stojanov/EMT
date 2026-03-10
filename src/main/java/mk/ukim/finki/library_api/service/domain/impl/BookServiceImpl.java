package mk.ukim.finki.library_api.service.domain.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.domain.Book;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import mk.ukim.finki.library_api.model.exception.BookNotFoundException;
import mk.ukim.finki.library_api.repository.BookRepository;
import mk.ukim.finki.library_api.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public List<Book> filter(Category category, State state, Long authorId) {
        if (category == null && state == null && authorId == null) {
            return bookRepository.findAll();
        }
        return bookRepository.filter(category, state, authorId);

    }
}
