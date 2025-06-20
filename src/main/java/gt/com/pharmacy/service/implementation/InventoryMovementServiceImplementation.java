package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.InventoryMovementDTO;
import gt.com.pharmacy.persistence.entity.InventoryMovementEntity;
import gt.com.pharmacy.persistence.entity.PresentationEntity;
import gt.com.pharmacy.persistence.entity.enums.MovementTypeEnum;
import gt.com.pharmacy.persistence.mapper.IInventoryMovementMapper;
import gt.com.pharmacy.persistence.repository.IInventoryMovementRepository;
import gt.com.pharmacy.persistence.repository.IPresentationRepository;
import gt.com.pharmacy.service.validator.InventoryMovementValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryMovementServiceImplementation extends AbstractCrudDtoServiceImplementation<InventoryMovementDTO, InventoryMovementEntity, Long> {

    private final IInventoryMovementMapper mapper;
    private final InventoryMovementValidator validator;
    private final IPresentationRepository repository;

    public InventoryMovementServiceImplementation(
            IInventoryMovementRepository iInventoryMovementRepository,
            IInventoryMovementMapper mapper,
            InventoryMovementValidator validator,
            IPresentationRepository repository
    ) {
        super(iInventoryMovementRepository);
        this.mapper = mapper;
        this.validator = validator;
        this.repository = repository;
    }

    @Override
    public InventoryMovementDTO toDTO(InventoryMovementEntity entity) {
        return mapper.toDto(entity);
    }

    @Override
    public InventoryMovementEntity toEntity(InventoryMovementDTO dto) {
        return mapper.toEntity(dto);
    }

    @Override
    @Transactional
    public InventoryMovementDTO save(InventoryMovementDTO dto) {
        validator.validateOnCreate(dto);
        PresentationEntity presentation = repository.findById(dto.getPresentation().getId())
                .orElseThrow(() -> new IllegalArgumentException("Presentation not found"));
        updateStock(presentation, dto.getType(), dto.getQuantity());
        InventoryMovementEntity movement = toEntity(dto);
        movement.setPresentation(presentation);
        InventoryMovementEntity saved = super.jpaRepository.save(movement);
        return toDTO(saved);
    }

    public int getCurrentStock(Long presentationId) {
        PresentationEntity presentation = repository.findById(presentationId).orElseThrow(() -> new IllegalArgumentException("Presentation not found"));
        return presentation.getCurrentStock();
    }

    private void updateStock(PresentationEntity presentation, MovementTypeEnum type, int quantity) {
        if (type == MovementTypeEnum.IN) {
            presentation.setCurrentStock(presentation.getCurrentStock() + quantity);
        } else {
            if (presentation.getCurrentStock() < quantity) {
                throw new IllegalArgumentException("Insufficient stock");
            }
            presentation.setCurrentStock(presentation.getCurrentStock() - quantity);
        }
        repository.save(presentation);
    }
}
