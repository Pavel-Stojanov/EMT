package mk.ukim.finki.library_api.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.dto.CreateBookDto;
import mk.ukim.finki.library_api.model.dto.DisplayBookDto;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import mk.ukim.finki.library_api.model.projections.BookExpandedProjection;
import mk.ukim.finki.library_api.model.projections.BookShortProjection;
import mk.ukim.finki.library_api.model.views.BookView;
import mk.ukim.finki.library_api.model.views.CategoryStatisticsView;
import mk.ukim.finki.library_api.service.application.BookApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookApplicationService bookApplicationService;

    @GetMapping
    public Page<DisplayBookDto> getAllBooks(@RequestParam(required = false) Category category,
                                            @RequestParam(required = false) State state,
                                            @RequestParam(required = false) Long authorId,
                                            @RequestParam(required = false) Boolean hasAvailable,
                                            Pageable pageable) {
        return bookApplicationService.getAllBooks(category, state, authorId,hasAvailable,pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookApplicationService.getBookById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> createBook(@Valid @RequestBody CreateBookDto bookDto) {
        DisplayBookDto displayBookDto = bookApplicationService.createBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(displayBookDto);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayBookDto> updateBook(@PathVariable Long id,
                                                     @Valid @RequestBody CreateBookDto bookDto) {
        DisplayBookDto displayBookDto = bookApplicationService.updateBook(id, bookDto);
        return ResponseEntity.ok(displayBookDto);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayBookDto> deleteBook(@PathVariable Long id) {
        bookApplicationService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/rent")
    public ResponseEntity<DisplayBookDto> markAsRented(@PathVariable Long id) {
        DisplayBookDto bookDto = bookApplicationService.markAsRented(id);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/projections/short")
    public Page<BookShortProjection> getShortProjections(Pageable pageable){
        return bookApplicationService.getShortProjections(pageable);
    }

    @GetMapping("/projections/expanded")
    public Page<BookExpandedProjection> getExpandedProjections(Pageable pageable){
        return bookApplicationService.getExpandedProjections(pageable);
    }

    @GetMapping("/views/database")
    public Page<BookView> getDatabaseView(Pageable pageable){
        return bookApplicationService.getDatabaseView(pageable);
    }

    @GetMapping("/views/materialized-statistics")
    public List<CategoryStatisticsView> getMaterializedViewStatistics(){
        return bookApplicationService.getMaterializedViewStatistics();
    }


}
