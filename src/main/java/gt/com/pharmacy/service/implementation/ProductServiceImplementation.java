package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.persistence.dto.ProductDTO;
import gt.com.pharmacy.persistence.entity.ProductEntity;
import gt.com.pharmacy.persistence.mapper.IProductMapper;
import gt.com.pharmacy.persistence.repository.IProductRepository;
import gt.com.pharmacy.service.validator.ProductValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImplementation extends AbstractCrudDtoServiceImplementation<ProductDTO, ProductEntity, Long> {

    private final IProductMapper iProductMapper;
    private final ProductValidator productValidator;

    public ProductServiceImplementation(
            IProductRepository iProductRepository,
            IProductMapper iProductMapper,
            ProductValidator productValidator
    ) {
        super(iProductRepository);
        this.iProductMapper = iProductMapper;
        this.productValidator = productValidator;
    }

    @Override
    public ProductDTO toDTO(ProductEntity entity) {
        return iProductMapper.toDto(entity);
    }

    @Override
    public ProductEntity toEntity(ProductDTO dto) {
        return iProductMapper.toEntity(dto);
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO dto) {
        productValidator.validateOnCreate(dto);
        return super.save(dto);
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO dto, Long id) {
        productValidator.validateOnUpdate(dto, id);
        return super.update(dto, id);
    }
}
