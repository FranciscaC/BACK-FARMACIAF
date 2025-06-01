package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.SaleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleItemRepository extends JpaRepository<SaleItemEntity, Long> {
}
