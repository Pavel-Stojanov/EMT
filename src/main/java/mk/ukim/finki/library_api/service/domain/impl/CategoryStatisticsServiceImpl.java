package mk.ukim.finki.library_api.service.domain.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.library_api.model.views.CategoryStatisticsView;
import mk.ukim.finki.library_api.repository.CategoryStatisticsRepository;
import mk.ukim.finki.library_api.service.domain.CategoryStatisticsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryStatisticsServiceImpl implements CategoryStatisticsService {
    private final CategoryStatisticsRepository categoryStatisticsRepository;

    @Override
    public List<CategoryStatisticsView> findAll() {
        return categoryStatisticsRepository.findAll();
    }
}
