package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.SaleDTO;
import gt.com.pharmacy.persistence.entity.SaleEntity;
import gt.com.pharmacy.persistence.mapper.ISaleMapper;
import gt.com.pharmacy.persistence.repository.ISaleRepository;
import gt.com.pharmacy.service.validator.SaleValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleServiceImplementation extends AbstractCrudDtoServiceImplementation<SaleDTO, SaleEntity, Long> {

    private final ISaleMapper iSaleMapper;
    private final SaleValidator saleValidator;

    public SaleServiceImplementation(
            ISaleRepository iSaleRepository,
            ISaleMapper iSaleMapper,
            SaleValidator saleValidator
    ) {
        super(iSaleRepository);
        this.iSaleMapper = iSaleMapper;
        this.saleValidator = saleValidator;
    }

    @Override
    public SaleDTO toDTO(SaleEntity entity) {
        return iSaleMapper.toDto(entity);
    }

    @Override
    public SaleEntity toEntity(SaleDTO dto) {
        return iSaleMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public SaleDTO save(SaleDTO dto) {
        saleValidator.validateOnCreate(dto);
        return super.save(dto);
    }

    @Override
    @Transactional
    public SaleDTO update(SaleDTO dto, Long id) {
        saleValidator.validateOnUpdate(dto);
        return super.update(dto, id);
    }
}
