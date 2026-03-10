package mk.ukim.finki.library_api.repository;

import mk.ukim.finki.library_api.model.domain.Book;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b from Book b WHERE " + "(:category is null or b.category = :category) and" +
            "(:state is null or b.state = :state) and" +
            "(:authorId is null or b.author.id = :authorId)")
    List<Book> filter(@Param("category") Category category,
                      @Param("state") State state,
                      @Param("authorId") Long authorId);
}
