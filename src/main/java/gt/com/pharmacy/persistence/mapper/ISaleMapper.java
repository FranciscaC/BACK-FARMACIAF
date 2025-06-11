package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.SaleDTO;
import gt.com.pharmacy.persistence.entity.SaleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISaleMapper {

    SaleDTO toDto(SaleEntity saleEntity);

    SaleEntity toEntity(SaleDTO saleDTO);
}
