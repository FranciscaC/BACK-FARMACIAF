package gt.com.pharmacy.persistence.repository;

import gt.com.pharmacy.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByFullNameAndIdNot(String fullName, Long idToExclude);

    boolean existsByNitAndIdNot(String nit, Long idToExclude);

    boolean existsByPhoneAndIdNot(String phone, Long idToExclude);

    boolean existsByEmailAndIdNot(String email, Long idToExclude);
}
