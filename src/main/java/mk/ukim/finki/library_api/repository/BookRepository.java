package mk.ukim.finki.library_api.repository;

import mk.ukim.finki.library_api.model.domain.Book;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import mk.ukim.finki.library_api.model.projections.BookExpandedProjection;
import mk.ukim.finki.library_api.model.projections.BookShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "author.country"})
    @Query("SELECT b from Book b WHERE " +
            "(:category is null or b.category = :category) and " +
            "(:state is null or b.state = :state) and " +
            "(:authorId is null or b.author.id = :authorId) and " +
            "(:hasAvailable is null or (:hasAvailable = true and b.availableCopies > 0) or (:hasAvailable = false and b.availableCopies = 0))")
    Page<Book> filter(@Param("category") Category category,
                      @Param("state") State state,
                      @Param("authorId") Long authorId,
                      @Param("hasAvailable") Boolean hasAvailable,
                      Pageable pageable);

    @Query("select b.id as id, b.name as name, b.category as category, b.state as state, b.availableCopies as availableCopies from Book b")
    Page<BookShortProjection> findAllShortProjections(Pageable pageable);

    @Query("select b.id as id, b.name as name, b.category as category, b.state as state, b.availableCopies as availableCopies," +
            "concat(b.author.name,' ',b.author.surname) as authorFullName, b.author.country.name as authorCountryName" +
            " from Book b")
    Page<BookExpandedProjection> findAllExpandedProjections(Pageable pageable);


}
