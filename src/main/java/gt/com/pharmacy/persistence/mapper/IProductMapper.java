package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.ProductDTO;
import gt.com.pharmacy.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductMapper {

    ProductDTO toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductDTO productDTO);
}
