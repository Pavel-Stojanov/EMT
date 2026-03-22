package mk.ukim.finki.library_api.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "category_statistics_mview")
public class CategoryStatisticsView {
    @Id
    private String category;
    private Long totalBooks;
    private Long totalAvailableCopies;
    private Long booksInBadState;

}
