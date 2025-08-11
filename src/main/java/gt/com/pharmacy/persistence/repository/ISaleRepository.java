package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {

    @Query("SELECT s FROM SaleEntity s WHERE DATE(s.date) = :date")
    List<SaleEntity> findByDate(@Param("date") LocalDate date);
}
