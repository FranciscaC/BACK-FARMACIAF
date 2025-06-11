package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.SaleItemDTO;
import gt.com.pharmacy.persistence.entity.SaleItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISaleItemMapper {

    SaleItemDTO toDto(SaleItemEntity saleItemEntity);

    SaleItemEntity toEntity(SaleItemDTO saleItemDTO);
}
