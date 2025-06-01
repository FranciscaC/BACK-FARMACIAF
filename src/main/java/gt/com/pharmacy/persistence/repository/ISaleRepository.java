package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {
}
