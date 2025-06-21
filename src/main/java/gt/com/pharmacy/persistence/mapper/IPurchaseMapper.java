package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.PurchaseDTO;
import gt.com.pharmacy.persistence.entity.PurchaseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPurchaseMapper {

    PurchaseDTO toDto(PurchaseEntity purchaseEntity);

    PurchaseEntity toEntity(PurchaseDTO purchaseDTO);

}
