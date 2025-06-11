package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.PresentationDTO;
import gt.com.pharmacy.persistence.entity.PresentationEntity;
import gt.com.pharmacy.persistence.mapper.IPresentationMapper;
import gt.com.pharmacy.persistence.repository.IPresentationRepository;
import gt.com.pharmacy.service.validator.PresentationValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        presentationValidator.validateOnCreate(dto);
        return super.save(dto);
    }
}
