package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.CustomerDTO;
import gt.com.pharmacy.persistence.entity.CustomerEntity;
import gt.com.pharmacy.persistence.mapper.ICustomerMapper;
import gt.com.pharmacy.persistence.repository.ICustomerRepository;
import gt.com.pharmacy.service.validator.CustomerValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImplementation extends AbstractCrudDtoServiceImplementation<CustomerDTO, CustomerEntity, Long> {

    private final ICustomerMapper iCustomerMapper;
    private final CustomerValidator customerValidator;

    public CustomerServiceImplementation(
            ICustomerRepository iCustomerRepository,
            ICustomerMapper iCustomerMapper,
            CustomerValidator customerValidator
    ) {
        super(iCustomerRepository);
        this.iCustomerMapper = iCustomerMapper;
        this.customerValidator = customerValidator;
    }

    @Override
    public CustomerDTO toDTO(CustomerEntity entity) {
        return iCustomerMapper.toDto(entity);
    }

    @Override
    public CustomerEntity toEntity(CustomerDTO dto) {
        return iCustomerMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO dto) {
        customerValidator.validateOnCreate(dto);

        return super.save(dto);
    }

    @Override
    @Transactional
    public CustomerDTO update(CustomerDTO dto, Long id) {
        customerValidator.validateOnUpdate(dto, id);
        CustomerEntity existingEntity = jpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        iCustomerMapper.updateEntityFromDto(dto, existingEntity);
        return super.update(dto, id);
    }
}
