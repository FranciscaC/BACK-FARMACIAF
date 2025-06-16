package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.ProductDTO;
import gt.com.pharmacy.persistence.entity.ProductEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface IProductMapper {

    ProductDTO toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductDTO productDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductDTO dto, @MappingTarget ProductEntity entity);
}
