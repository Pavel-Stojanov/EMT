package mk.ukim.finki.library_api.repository;

import mk.ukim.finki.library_api.model.domain.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {
}
