package mk.ukim.finki.library_api.service.application.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.domain.Author;
import mk.ukim.finki.library_api.model.domain.Book;
import mk.ukim.finki.library_api.model.dto.CreateBookDto;
import mk.ukim.finki.library_api.model.dto.DisplayBookDto;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import mk.ukim.finki.library_api.model.exception.NoAvailableCopiesException;
import mk.ukim.finki.library_api.service.application.BookApplicationService;
import mk.ukim.finki.library_api.service.domain.AuthorService;
import mk.ukim.finki.library_api.service.domain.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;

    @Override
    public List<DisplayBookDto> getAllBooks( Category category, State state, Long authorId) {
        return DisplayBookDto.from(bookService.filter(category,state,authorId));
    }

    @Override
    public DisplayBookDto getBookById(Long id) {
        Book book = bookService.findById(id);
        return DisplayBookDto.from(book);
    }

    @Override
    @Transactional
    public DisplayBookDto createBook(CreateBookDto bookDto) {
        Author author = authorService.findById(bookDto.authorId());

        Book bookToSave = bookDto.toBook(author);

        Book savedBook = bookService.save(bookToSave);
        return DisplayBookDto.from(savedBook);
    }

    @Override
    @Transactional
    public DisplayBookDto updateBook(Long id, CreateBookDto bookDto) {
        Book book = bookService.findById(id);
        Author author = authorService.findById(bookDto.authorId());

        book.setName(bookDto.name());
        book.setCategory(bookDto.category());
        book.setAuthor(author);
        book.setState(bookDto.state());
        book.setAvailableCopies(bookDto.availableCopies());

        Book updatedBook = bookService.save(book);
        return DisplayBookDto.from(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookService.findById(id);

        if (book.getState() == State.GOOD) {
            throw new RuntimeException("Не може да се избрише книга која е во добра состојба.");
        }

        bookService.delete(book);

    }

    @Override
    @Transactional
    public DisplayBookDto markAsRented(Long id) {
        Book book = bookService.findById(id);

        if (book.getAvailableCopies() <= 0) {
            throw new NoAvailableCopiesException(id);
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        Book updatedBook = bookService.save(book);

        return DisplayBookDto.from(updatedBook);
    }
}
