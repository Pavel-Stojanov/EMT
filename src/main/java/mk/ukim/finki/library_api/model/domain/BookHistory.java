package mk.ukim.finki.library_api.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookHistory {
    @Id
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_history_descriptions",joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "description")
    private List<String> descriptions = new ArrayList<>();

    public BookHistory(Long id) {
        this.id = id;
    }

    public void addDescription(String description) {
        descriptions.add(description);
    }
}
