package co.gov.ids.stationerycontrol.delivery.framework.persistence.mapper;

import co.gov.ids.stationerycontrol.delivery.domain.Delivery;
import co.gov.ids.stationerycontrol.delivery.framework.persistence.entities.DeliveryEntity;

/**
 * Class to map Delivery and DeliveryEntity.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public final class DeliveryMapper {

    private DeliveryMapper() {}

    /**
     * Function to map Entity to Domain.
     *
     * @param entity DeliveryEntity to be mapped.
     * @return Delivery mapped.
     */
    public static Delivery toDomain(DeliveryEntity entity) {
        Delivery domain = new Delivery();
        domain.setTradeNumber(entity.getTradeNumber());
        domain.setDate(entity.getDate());
        domain.setDeliveryType(entity.getDeliveryType());
        domain.setFirstCertificate(entity.getFirstCertificate());
        domain.setLastCertificate(entity.getLastCertificate());
        domain.setSender(entity.getSender());
        domain.setReceiver(entity.getReceiver());
        return domain;
    }

    /**
     * Function to map Domain to Entity.
     *
     * @param domain Delivery to be mapped.
     * @return DeliveryEntity mapped.
     */
    public static DeliveryEntity toEntity(Delivery domain) {
        DeliveryEntity entity = new DeliveryEntity();
        entity.setTradeNumber(domain.getTradeNumber());
        entity.setDate(domain.getDate());
        entity.setDeliveryType(domain.getDeliveryType());
        entity.setFirstCertificate(domain.getFirstCertificate());
        entity.setLastCertificate(domain.getLastCertificate());
        entity.setSender(domain.getSender());
        entity.setReceiver(domain.getReceiver());
        return entity;
    }

}
