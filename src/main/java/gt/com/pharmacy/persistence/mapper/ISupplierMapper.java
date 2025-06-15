package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.entity.SupplierEntity;
import gt.com.pharmacy.persistence.dto.SupplierDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ISupplierMapper {

    SupplierDTO toDto(SupplierEntity supplierEntity);

    SupplierEntity toEntity(SupplierDTO supplierDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(SupplierDTO dto, @MappingTarget SupplierEntity entity);
}
