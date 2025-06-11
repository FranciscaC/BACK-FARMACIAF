package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.InventoryMovementDTO;
import gt.com.pharmacy.persistence.entity.InventoryMovementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IInventoryMovementMapper {

    InventoryMovementDTO toDto(InventoryMovementEntity inventoryMovementEntity);

    InventoryMovementEntity toEntity(InventoryMovementDTO inventoryMovementDTO);
}
