package backend.db.repo;

import backend.db.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CalcRepo extends JpaRepository<Log, Long> {
    Page<Log> findByOrderByDateTimeDesc(Pageable pageable);
}
