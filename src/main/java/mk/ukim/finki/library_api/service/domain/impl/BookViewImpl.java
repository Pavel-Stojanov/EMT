package mk.ukim.finki.library_api.service.domain.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.views.BookView;
import mk.ukim.finki.library_api.repository.BookViewRepository;
import mk.ukim.finki.library_api.service.domain.BookViewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookViewImpl implements BookViewService {
    private final BookViewRepository bookViewRepository;

    @Override
    public Page<BookView> findAll(Pageable pageable) {
        return bookViewRepository.findAll(pageable);
    }
}
