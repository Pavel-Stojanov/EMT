package mk.ukim.finki.library_api.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE book SET deleted = TRUE where id = ?")
@SQLRestriction("deleted = false")
public class Book extends BaseAuditableEntity{
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    @Enumerated(EnumType.STRING)
    private State state;

    private Integer availableCopies;

    public Book(String name, Category category, Author author, State state, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.state = state;
        this.availableCopies = availableCopies;
    }
}
