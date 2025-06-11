package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.PresentationDTO;
import gt.com.pharmacy.persistence.entity.PresentationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPresentationMapper {

    PresentationDTO toDto(PresentationEntity presentationEntity);

    PresentationEntity toEntity(PresentationDTO presentationDTO);
}
