package co.gov.ids.stationerycontrol.delivery.persistence.mapper;

import java.util.List;
import org.mapstruct.*;
import co.gov.ids.stationerycontrol.delivery.domain.dto.Delivery;
import co.gov.ids.stationerycontrol.delivery.persistence.entities.DeliveryEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DeliveryMapper {

    @Mappings({
            @Mapping(source = "tradeNumber", target = "tradeNumber"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "deliveryType", target = "deliveryType"),
            @Mapping(source = "firstCertificate", target = "firstCertificate"),
            @Mapping(source = "lastCertificate", target = "lastCertificate"),
            @Mapping(source = "sender", target = "sender"),
            @Mapping(source = "receiver", target = "receiver"),
    })
    Delivery toDelivery(DeliveryEntity entity);

    List<Delivery> toDeliveries(List<DeliveryEntity> entities);

    @InheritInverseConfiguration
    DeliveryEntity toEntity(Delivery domain);

}
