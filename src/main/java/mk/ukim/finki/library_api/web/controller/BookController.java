package mk.ukim.finki.library_api.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.dto.CreateBookDto;
import mk.ukim.finki.library_api.model.dto.DisplayBookDto;
import mk.ukim.finki.library_api.service.application.BookApplicationService;
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
    public List<DisplayBookDto> getAllBooks() {
        return bookApplicationService.getAllBooks();
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
    public ResponseEntity<DisplayBookDto> updateBook(@PathVariable Long id, @Valid @RequestBody CreateBookDto bookDto) {
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


}
