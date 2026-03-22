package mk.ukim.finki.library_api.model.views;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "book_view")
public class BookView {
    @Id
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private State state;
    private Integer availableCopies;
    private String authorFullName;
    private String countryName;
}
