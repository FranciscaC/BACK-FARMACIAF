package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.SaleDTO;
import gt.com.pharmacy.persistence.dto.SaleItemDTO;
import gt.com.pharmacy.persistence.entity.SaleEntity;
import gt.com.pharmacy.persistence.entity.SaleItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ISaleMapper {

    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "items", source = "items")
    SaleDTO toDto(SaleEntity saleEntity);

    @Mapping(target = "items", ignore = true)
    SaleEntity toEntity(SaleDTO saleDTO);

    @Mapping(target = "presentationId", source = "presentation.id")
    SaleItemDTO saleItemToDto(SaleItemEntity saleItemEntity);

    default List<SaleItemDTO> mapItems(List<SaleItemEntity> items) {
        return items.stream()
                .map(this::saleItemToDto)
                .collect(Collectors.toList());
    }
}
