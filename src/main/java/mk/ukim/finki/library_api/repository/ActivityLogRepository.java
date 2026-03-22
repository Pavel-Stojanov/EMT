package mk.ukim.finki.library_api.repository;

import mk.ukim.finki.library_api.model.domain.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long> {
}
