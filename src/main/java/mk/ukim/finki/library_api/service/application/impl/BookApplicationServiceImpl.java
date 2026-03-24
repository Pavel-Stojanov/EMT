package mk.ukim.finki.library_api.service.application.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.events.BookRentedEvent;
import mk.ukim.finki.library_api.model.domain.Author;
import mk.ukim.finki.library_api.model.domain.Book;
import mk.ukim.finki.library_api.model.dto.CreateBookDto;
import mk.ukim.finki.library_api.model.dto.DisplayBookDto;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import mk.ukim.finki.library_api.model.exception.NoAvailableCopiesException;
import mk.ukim.finki.library_api.model.projections.BookExpandedProjection;
import mk.ukim.finki.library_api.model.projections.BookShortProjection;
import mk.ukim.finki.library_api.model.views.BookView;
import mk.ukim.finki.library_api.model.views.CategoryStatisticsView;
import mk.ukim.finki.library_api.service.application.BookApplicationService;
import mk.ukim.finki.library_api.service.domain.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ApplicationEventPublisher eventPublisher;
    private final BookViewService bookViewService;
    private final CategoryStatisticsService categoryStatisticsService;
    private final BookHistoryService bookHistoryService;

    @Override
    public Page<DisplayBookDto> getAllBooks(Category category, State state, Long authorId, Boolean hasAvailable, Pageable pageable) {
        return bookService.filter(category, state, authorId, hasAvailable, pageable).map(DisplayBookDto::from);
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

        bookHistoryService.addHistoryEntry(savedBook.getId(), "Added book with id " + savedBook.getId() + " in " + LocalDateTime.now());

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

        bookHistoryService.addHistoryEntry(updatedBook.getId(), "Updated book with id " + updatedBook.getId() + " in " + LocalDateTime.now());

        return DisplayBookDto.from(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookService.findById(id);

        if (book.getState() == State.GOOD) {
            throw new RuntimeException("Не може да се избрише книга која е во добра состојба.");
        }

        bookHistoryService.addHistoryEntry(book.getId(), "Deleted book with id " + book.getId() + " in " + LocalDateTime.now());


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

        eventPublisher.publishEvent(new BookRentedEvent(book.getId(), book.getName(), book.getAvailableCopies()));

        bookHistoryService.addHistoryEntry(updatedBook.getId(), "Rented book with id " + updatedBook.getId() + " in " + LocalDateTime.now());


        return DisplayBookDto.from(updatedBook);
    }

    @Override
    public Page<BookShortProjection> getShortProjections(Pageable pageable) {
        return bookService.findAllShortProjections(pageable);
    }

    @Override
    public Page<BookExpandedProjection> getExpandedProjections(Pageable pageable) {
        return bookService.findAllExpandedProjections(pageable);
    }

    @Override
    public Page<BookView> getDatabaseView(Pageable pageable) {
        return bookViewService.findAll(pageable);
    }

    @Override
    public List<CategoryStatisticsView> getMaterializedViewStatistics() {
        return categoryStatisticsService.findAll();
    }
}
