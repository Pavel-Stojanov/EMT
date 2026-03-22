package mk.ukim.finki.library_api.repository;

import mk.ukim.finki.library_api.model.views.CategoryStatisticsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryStatisticsRepository extends JpaRepository<CategoryStatisticsView, Long> {

    @Modifying
    @Transactional
    @Query(value = "refresh materialized view category_statistics_mview", nativeQuery = true)
    void refresh();
}
