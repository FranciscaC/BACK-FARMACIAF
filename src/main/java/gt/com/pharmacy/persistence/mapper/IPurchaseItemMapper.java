package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.PurchaseItemDTO;
import gt.com.pharmacy.persistence.entity.PurchaseItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { IPresentationMapper.class })
public interface IPurchaseItemMapper {

    PurchaseItemDTO toDto(PurchaseItemEntity entity);

    PurchaseItemEntity toEntity(PurchaseItemDTO dto);
}
