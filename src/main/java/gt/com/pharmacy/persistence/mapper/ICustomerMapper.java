package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.CustomerDTO;
import gt.com.pharmacy.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {

    CustomerDTO toDto(CustomerEntity customerEntity);

    CustomerEntity toEntity(CustomerDTO customerDTO);
}
