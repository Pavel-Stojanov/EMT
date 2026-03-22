package mk.ukim.finki.library_api.service.domain;

import mk.ukim.finki.library_api.model.views.BookView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookViewService {
    Page<BookView> findAll(Pageable pageable);
}
