package mk.ukim.finki.library_api.service.domain;

import mk.ukim.finki.library_api.model.views.CategoryStatisticsView;

import java.util.List;

public interface CategoryStatisticsService {
    List<CategoryStatisticsView> findAll();
}
