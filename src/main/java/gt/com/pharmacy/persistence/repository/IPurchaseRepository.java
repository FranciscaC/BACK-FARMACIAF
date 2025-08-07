package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    @Query("SELECT DISTINCT p FROM PurchaseEntity p LEFT JOIN FETCH p.items WHERE DATE(p.purchaseDate) = :date")
    List<PurchaseEntity> findByPurchaseDate(LocalDate date);
}
