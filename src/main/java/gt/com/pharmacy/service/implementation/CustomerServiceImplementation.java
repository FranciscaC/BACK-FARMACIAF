package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.CustomerDTO;
import gt.com.pharmacy.persistence.entity.CustomerEntity;
import gt.com.pharmacy.persistence.mapper.ICustomerMapper;
import gt.com.pharmacy.persistence.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImplementation extends AbstractCrudDtoServiceImplementation<CustomerDTO, CustomerEntity, Long> {

    private final ICustomerMapper iCustomerMapper;

    public CustomerServiceImplementation(ICustomerRepository iCustomerRepository, ICustomerMapper iCustomerMapper) {
        super(iCustomerRepository);
        this.iCustomerMapper = iCustomerMapper;
    }

    @Override
    public CustomerDTO toDTO(CustomerEntity entity) {return iCustomerMapper.toDto(entity);}

    @Override
    public CustomerEntity toEntity(CustomerDTO dto) {return iCustomerMapper.toEntity(dto);}

    @Override
    protected void updateEntityFromDto(CustomerDTO dto, CustomerEntity entity) {
        if (dto.getFullName() != null) entity.setFullName(dto.getFullName());
        if (dto.getNit() != null) entity.setNit(dto.getNit());
        if (dto.getPhone() != null) entity.setPhone(dto.getPhone());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
    }
}
