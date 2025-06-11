package gt.com.pharmacy.persistence.mapper;

import gt.com.pharmacy.persistence.dto.PriceHistoryDTO;
import gt.com.pharmacy.persistence.entity.PriceHistoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPriceHistoryMapper {

    PriceHistoryDTO toDto(PriceHistoryEntity priceHistoryEntity);

    PriceHistoryEntity toEntity(PriceHistoryDTO priceHistoryDTO);
}
