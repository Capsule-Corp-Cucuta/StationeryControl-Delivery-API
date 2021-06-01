package co.gov.ids.stationerycontrol.delivery.persistence.repositories;

import java.util.Optional;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.gov.ids.stationerycontrol.delivery.domain.dto.DeliveryType;
import org.springframework.data.repository.PagingAndSortingRepository;
import co.gov.ids.stationerycontrol.delivery.persistence.entities.DeliveryEntity;

public interface IDeliveryJPARepository extends PagingAndSortingRepository<DeliveryEntity, String> {

    Optional<Page<DeliveryEntity>> findByDate(LocalDate date, Pageable page);

    Optional<Page<DeliveryEntity>> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable page);

    Optional<Page<DeliveryEntity>> findByDeliveryType(DeliveryType type, Pageable page);

    Optional<Page<DeliveryEntity>> findByDeliveryTypeAndSenderOrReceiver(DeliveryType type, String sender,
                                                                         String receiver, Pageable page);

    Optional<Page<DeliveryEntity>> findBySenderOrReceiver(String sender, String receiver, Pageable page);

    long countDeliveriesBySenderOrReceiver(String sender, String receiver);

}
