package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.PurchaseDTO;
import gt.com.pharmacy.persistence.entity.PurchaseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { IPurchaseItemMapper.class, ISupplierMapper.class })
public interface IPurchaseMapper {

    PurchaseDTO toDto(PurchaseEntity entity);

    PurchaseEntity toEntity(PurchaseDTO dto);
}
