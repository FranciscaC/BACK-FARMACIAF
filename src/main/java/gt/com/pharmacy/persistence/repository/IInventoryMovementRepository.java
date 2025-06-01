package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.InventoryMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryMovementRepository extends JpaRepository<InventoryMovementEntity, Long> {
}
