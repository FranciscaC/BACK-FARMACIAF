package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.PresentationDTO;
import gt.com.pharmacy.persistence.entity.PresentationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IPresentationMapper {

    @Mapping(source = "supplier", target = "supplier")
    PresentationDTO toDto(PresentationEntity presentationEntity);

    @Mapping(target = "priceHistory", ignore = true)
    @Mapping(target = "movements", ignore = true)
    PresentationEntity toEntity(PresentationDTO presentationDTO);
}
