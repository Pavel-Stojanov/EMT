package mk.ukim.finki.library_api.repository;

import mk.ukim.finki.library_api.model.views.BookView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookViewRepository extends JpaRepository<BookView, Long> {
}
