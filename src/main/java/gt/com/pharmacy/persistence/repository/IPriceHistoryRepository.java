package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.PriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPriceHistoryRepository extends JpaRepository<PriceHistoryEntity,Long> {
}
