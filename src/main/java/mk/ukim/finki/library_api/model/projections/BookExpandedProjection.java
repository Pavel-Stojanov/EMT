package mk.ukim.finki.library_api.model.projections;

import mk.ukim.finki.library_api.model.enums.Category;
import mk.ukim.finki.library_api.model.enums.State;

public interface BookExpandedProjection {
    Long getId();

    String getName();

    Category getCategory();

    State getState();

    Integer getAvailableCopies();

    String getAuthorFullName();

    String getAuthorCountryName();

}
