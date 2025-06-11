package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.InventoryMovementDTO;
import gt.com.pharmacy.persistence.entity.InventoryMovementEntity;
import gt.com.pharmacy.persistence.mapper.IInventoryMovementMapper;
import gt.com.pharmacy.persistence.repository.IInventoryMovementRepository;
import gt.com.pharmacy.service.validator.InventoryMovementValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryMovementServiceImplementation extends AbstractCrudDtoServiceImplementation<InventoryMovementDTO, InventoryMovementEntity, Long> {

    private final IInventoryMovementMapper iInventoryMovementMapper;
    private final InventoryMovementValidator inventoryMovementValidator;

    public InventoryMovementServiceImplementation(
            IInventoryMovementRepository iInventoryMovementRepository,
            IInventoryMovementMapper iInventoryMovementMapper,
            InventoryMovementValidator inventoryMovementValidator
    ) {
        super(iInventoryMovementRepository);
        this.iInventoryMovementMapper = iInventoryMovementMapper;
        this.inventoryMovementValidator = inventoryMovementValidator;
    }

    @Override
    public InventoryMovementDTO toDTO(InventoryMovementEntity entity) {
        return iInventoryMovementMapper.toDto(entity);
    }

    @Override
    public InventoryMovementEntity toEntity(InventoryMovementDTO dto) {
        return iInventoryMovementMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public InventoryMovementDTO save(InventoryMovementDTO dto) {
        inventoryMovementValidator.validateOnCreate(dto);
        return super.save(dto);
    }

    @Override
    @Transactional
    public InventoryMovementDTO update(InventoryMovementDTO dto, Long id) {
        inventoryMovementValidator.validateOnUpdate(dto);
        return super.update(dto, id);
    }
}