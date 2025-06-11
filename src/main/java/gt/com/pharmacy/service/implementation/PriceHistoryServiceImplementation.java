package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.PriceHistoryDTO;
import gt.com.pharmacy.persistence.entity.PriceHistoryEntity;
import gt.com.pharmacy.persistence.mapper.IPriceHistoryMapper;
import gt.com.pharmacy.persistence.repository.IPriceHistoryRepository;
import gt.com.pharmacy.service.validator.PriceHistoryValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PriceHistoryServiceImplementation extends AbstractCrudDtoServiceImplementation<PriceHistoryDTO, PriceHistoryEntity, Long> {

    private final IPriceHistoryMapper iPriceHistoryMapper;
    private final PriceHistoryValidator priceHistoryValidator;

    public PriceHistoryServiceImplementation(
            IPriceHistoryRepository iPriceHistoryRepository,
            IPriceHistoryMapper iPriceHistoryMapper,
            PriceHistoryValidator priceHistoryValidator
    ) {
        super(iPriceHistoryRepository);
        this.iPriceHistoryMapper = iPriceHistoryMapper;
        this.priceHistoryValidator = priceHistoryValidator;
    }

    @Override
    public PriceHistoryDTO toDTO(PriceHistoryEntity entity) {
        return iPriceHistoryMapper.toDto(entity);
    }

    @Override
    public PriceHistoryEntity toEntity(PriceHistoryDTO dto) {
        return iPriceHistoryMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public PriceHistoryDTO save(PriceHistoryDTO dto) {
        priceHistoryValidator.validateOnCreate(dto);
        return super.save(dto);
    }

}
