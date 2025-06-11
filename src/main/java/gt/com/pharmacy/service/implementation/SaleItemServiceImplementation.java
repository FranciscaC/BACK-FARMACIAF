package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.SaleItemDTO;
import gt.com.pharmacy.persistence.entity.SaleItemEntity;
import gt.com.pharmacy.persistence.mapper.ISaleItemMapper;
import gt.com.pharmacy.persistence.repository.ISaleItemRepository;
import gt.com.pharmacy.service.validator.SaleItemValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleItemServiceImplementation extends AbstractCrudDtoServiceImplementation<SaleItemDTO, SaleItemEntity, Long> {

    private final ISaleItemMapper iSaleItemMapper;
    private final SaleItemValidator saleItemValidator;

    public SaleItemServiceImplementation(
            ISaleItemRepository iSaleItemRepository,
            ISaleItemMapper iSaleItemMapper,
            SaleItemValidator saleItemValidator
    ) {
        super(iSaleItemRepository);
        this.iSaleItemMapper = iSaleItemMapper;
        this.saleItemValidator = saleItemValidator;
    }

    @Override
    public SaleItemDTO toDTO(SaleItemEntity entity) {
        return iSaleItemMapper.toDto(entity);
    }

    @Override
    public SaleItemEntity toEntity(SaleItemDTO dto) {
        return iSaleItemMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public SaleItemDTO save(SaleItemDTO dto) {
        saleItemValidator.validateOnCreate(dto);
        return super.save(dto);
    }
}
