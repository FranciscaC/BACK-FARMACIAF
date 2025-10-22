package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.PresentationDTO;
import gt.com.pharmacy.persistence.entity.PresentationEntity;
import gt.com.pharmacy.persistence.entity.PriceHistoryEntity;
import gt.com.pharmacy.persistence.mapper.IPresentationMapper;
import gt.com.pharmacy.persistence.model.Price;
import gt.com.pharmacy.persistence.model.Supplier;
import gt.com.pharmacy.persistence.repository.IPresentationRepository;
import gt.com.pharmacy.service.validator.PresentationValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class PresentationServiceImplementation extends AbstractCrudDtoServiceImplementation<PresentationDTO, PresentationEntity, Long> {

    private final IPresentationMapper iPresentationMapper;
    private final PresentationValidator presentationValidator;

    public PresentationServiceImplementation(
            IPresentationRepository iPresentationRepository,
            IPresentationMapper iPresentationMapper,
            PresentationValidator presentationValidator
    ) {
        super(iPresentationRepository);
        this.iPresentationMapper = iPresentationMapper;
        this.presentationValidator = presentationValidator;
    }

    @Override
    public PresentationDTO toDTO(PresentationEntity entity) {
        return iPresentationMapper.toDto(entity);
    }

    @Override
    public PresentationEntity toEntity(PresentationDTO dto) {
        return iPresentationMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public PresentationDTO save(PresentationDTO dto) {
        presentationValidator.validate(dto);
        PresentationEntity presentation = iPresentationMapper.toEntity(dto);
        if (dto.getSupplier() != null) {
            Supplier supplier = new Supplier();
            supplier.setLaboratory(dto.getSupplier().getLaboratory());
            supplier.setPhone(dto.getSupplier().getPhone());
            presentation.setSupplier(supplier);
        }
        presentation.setCurrentStock(dto.getCurrentStock());
        if (presentation.getPriceHistory() == null) {
            presentation.setPriceHistory(new ArrayList<>());
        }
        PriceHistoryEntity history = PriceHistoryEntity.builder()
                .price(presentation.getCurrentPrice())
                .effectiveFrom(LocalDateTime.now())
                .presentation(presentation)
                .build();
        presentation.getPriceHistory().add(history);
        presentation.setAvailable(true);
        PresentationEntity saved = jpaRepository.save(presentation);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public PresentationDTO update(PresentationDTO dto, Long id) {
        presentationValidator.validate(dto);
        PresentationEntity existing = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Presentation not found with id: " + id));
        existing.setDescription(dto.getDescription());
        existing.setCurrentStock(dto.getCurrentStock());
        if (dto.getSupplier() != null) {
            if (existing.getSupplier() == null) {
                existing.setSupplier(new Supplier());
            }
            existing.getSupplier().setLaboratory(dto.getSupplier().getLaboratory());
            existing.getSupplier().setPhone(dto.getSupplier().getPhone());
        }
        Price newPrice = dto.getCurrentPrice();
        if (newPrice != null && !newPrice.equals(existing.getCurrentPrice())) {
            existing.getPriceHistory().stream()
                    .filter(priceHistory -> priceHistory.getEffectiveTo() == null)
                    .forEach(priceHistory -> priceHistory.setEffectiveTo(LocalDateTime.now()));
            PriceHistoryEntity newHistory = PriceHistoryEntity.builder()
                    .price(newPrice)
                    .effectiveFrom(LocalDateTime.now())
                    .presentation(existing)
                    .build();
            existing.getPriceHistory().add(newHistory);
            existing.setCurrentPrice(newPrice);
        }
        if (dto.getCurrentStock() != null) {
            existing.setAvailable(dto.getCurrentStock() > 0);
        }
        PresentationEntity updated = jpaRepository.save(existing);
        return toDTO(updated);
    }

    @Transactional
    public PresentationDTO adjustStock(Long presentationId, Integer quantity) {
        PresentationEntity presentation = jpaRepository.findById(presentationId)
                .orElseThrow(() -> new EntityNotFoundException("Presentation not found with id: " + presentationId));

        int newStock = presentation.getCurrentStock() + quantity;
        if (newStock < 0) {
            throw new IllegalArgumentException("Insufficient stock. Current: " + presentation.getCurrentStock() +
                    ", Adjustment: " + quantity);
        }

        presentation.setCurrentStock(newStock);
        PresentationEntity updated = jpaRepository.save(presentation);
        return toDTO(updated);
    }
}
