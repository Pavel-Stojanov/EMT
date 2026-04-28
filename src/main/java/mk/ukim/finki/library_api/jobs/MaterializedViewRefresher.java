package mk.ukim.finki.library_api.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.library_api.repository.CategoryStatisticsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class MaterializedViewRefresher {
    private final CategoryStatisticsRepository categoryStatisticsRepository;

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void refreshMaterializedView() {
        log.info("Refreshing category_statistics_mview...");
        categoryStatisticsRepository.refresh();
        log.info("Finished refresh for category_statistics_mview.");
    }
}
