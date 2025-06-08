package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.entity.SupplierEntity;
import gt.com.pharmacy.persistence.mapper.ISupplierMapper;
import gt.com.pharmacy.persistence.repository.ISupplierRepository;
import gt.com.pharmacy.presentation.dto.SupplierDTO;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImplementation extends AbstractCrudDtoServiceImplementation<SupplierDTO, SupplierEntity, Long> {

    private final ISupplierMapper iSupplierMapper;

    public SupplierServiceImplementation(ISupplierRepository iSupplierRepository, ISupplierMapper iSupplierMapper) {
        super(iSupplierRepository);
        this.iSupplierMapper = iSupplierMapper;
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
    protected void updateEntityFromDto(SupplierDTO dto, SupplierEntity entity) {
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getAddress() != null) entity.setAddress(dto.getAddress());
        if (dto.getPhone() != null) entity.setPhone(dto.getPhone());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
    }
}
