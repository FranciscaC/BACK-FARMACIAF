package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.entity.SupplierEntity;
import gt.com.pharmacy.persistence.mapper.ISupplierMapper;
import gt.com.pharmacy.persistence.repository.ISupplierRepository;
import gt.com.pharmacy.persistence.dto.SupplierDTO;
import gt.com.pharmacy.service.validator.SupplierValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierServiceImplementation extends AbstractCrudDtoServiceImplementation<SupplierDTO, SupplierEntity, Long> {

    private final ISupplierMapper iSupplierMapper;

    private final SupplierValidator supplierValidator;

    public SupplierServiceImplementation(
            ISupplierRepository iSupplierRepository,
            ISupplierMapper iSupplierMapper,
            SupplierValidator supplierValidator
    ) {
        super(iSupplierRepository);
        this.iSupplierMapper = iSupplierMapper;
        this.supplierValidator = supplierValidator;
    }

    @Override
    public SupplierDTO toDTO(SupplierEntity entity) {
        return iSupplierMapper.toDto(entity);
    }

    @Override
    public SupplierEntity toEntity(SupplierDTO dto) {
        return iSupplierMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public SupplierDTO save(SupplierDTO dto) {
        supplierValidator.validateOnCreate(dto);
        return super.save(dto);
    }

    @Override
    @Transactional
    public SupplierDTO update(SupplierDTO dto, Long id) {
        supplierValidator.validateOnUpdate(dto, id);
        SupplierEntity existingEntity = jpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
        iSupplierMapper.updateEntityFromDto(dto, existingEntity);
        return super.update(dto, id);
    }
}
