package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByCodeAndIdNot(String code, Long idToExclude);
}
