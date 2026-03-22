package mk.ukim.finki.library_api.service.application;

import mk.ukim.finki.library_api.model.dto.CreateBookDto;
import mk.ukim.finki.library_api.model.dto.DisplayBookDto;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import mk.ukim.finki.library_api.model.projections.BookExpandedProjection;
import mk.ukim.finki.library_api.model.projections.BookShortProjection;
import mk.ukim.finki.library_api.model.views.BookView;
import mk.ukim.finki.library_api.model.views.CategoryStatisticsView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookApplicationService {
    Page<DisplayBookDto> getAllBooks(Category category, State state, Long authorId, Boolean hasAvailable, Pageable pageable);

    DisplayBookDto getBookById(Long id);

    DisplayBookDto createBook(CreateBookDto bookDto);

    DisplayBookDto updateBook(Long id, CreateBookDto bookDto);

    void deleteBook(Long id);

    DisplayBookDto markAsRented(Long id);

    Page<BookShortProjection> getShortProjections(Pageable pageable);

    Page<BookExpandedProjection> getExpandedProjections(Pageable pageable);

    Page<BookView> getDatabaseView(Pageable pageable);

    List<CategoryStatisticsView> getMaterializedViewStatistics();


}
