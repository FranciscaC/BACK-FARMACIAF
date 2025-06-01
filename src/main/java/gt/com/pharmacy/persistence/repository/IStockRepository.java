package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends JpaRepository<StockEntity, Long> {
}
