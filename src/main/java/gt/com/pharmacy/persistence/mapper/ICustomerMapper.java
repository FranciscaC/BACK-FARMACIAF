package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.CustomerDTO;
import gt.com.pharmacy.persistence.entity.CustomerEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {

    CustomerDTO toDto(CustomerEntity customerEntity);

    CustomerEntity toEntity(CustomerDTO customerDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CustomerDTO dto, @MappingTarget CustomerEntity entity);
}
