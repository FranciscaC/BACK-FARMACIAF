package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
}
