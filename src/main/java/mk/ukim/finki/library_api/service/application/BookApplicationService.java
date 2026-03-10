package mk.ukim.finki.library_api.service.application;

import mk.ukim.finki.library_api.model.dto.CreateBookDto;
import mk.ukim.finki.library_api.model.dto.DisplayBookDto;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;

import java.util.List;

public interface BookApplicationService {
    List<DisplayBookDto> getAllBooks(Category category, State state, Long authorId);

    DisplayBookDto getBookById(Long id);

    DisplayBookDto createBook(CreateBookDto bookDto);

    DisplayBookDto updateBook(Long id, CreateBookDto bookDto);

    void deleteBook(Long id);

    DisplayBookDto markAsRented(Long id);


}
