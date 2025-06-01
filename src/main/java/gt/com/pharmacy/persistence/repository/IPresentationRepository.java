package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.PresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPresentationRepository extends JpaRepository<PresentationEntity, Long> {
}
