package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.entity.SupplierEntity;
import gt.com.pharmacy.presentation.dto.SupplierDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplierMapper {

    SupplierDTO toDto(SupplierEntity supplierEntity);

    SupplierEntity toEntity(SupplierDTO supplierDTO);
}
