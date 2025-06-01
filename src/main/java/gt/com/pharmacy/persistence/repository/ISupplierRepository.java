package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long> {
}
