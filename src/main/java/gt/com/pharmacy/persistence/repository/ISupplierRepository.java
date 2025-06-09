package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long> {

    boolean existsByNameAndIdNot(String name, Long idToExclude);

    boolean existsByPhoneAndIdNot(String phone, Long idToExclude);

    boolean existsByEmailAndIdNot(String email, Long idToExclude);
}
