package mk.ukim.finki.library_api.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Country extends BaseEntity{
    private String name;
    private String continent;
}
