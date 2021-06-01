package co.gov.ids.stationerycontrol.delivery.domain.repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import co.gov.ids.stationerycontrol.delivery.domain.dto.Delivery;
import co.gov.ids.stationerycontrol.delivery.domain.dto.DeliveryType;

public interface IDeliveryRepository {

    Delivery create(Delivery delivery);

    Optional<Delivery> findByTradeNumber(String tradeNumber);

    Optional<List<Delivery>> findAll(int page);

    Optional<List<Delivery>> findByDate(LocalDate date, int page);

    Optional<List<Delivery>> findByDateBetween(LocalDate startDate, LocalDate endDate, int page);

    Optional<List<Delivery>> findByDeliveryType(DeliveryType type, int page);

    Optional<List<Delivery>> findByDeliveryTypeAndSenderOrReceiver(DeliveryType type, String user, int page);

    Optional<List<Delivery>> findBySenderOrReceiver(String user, int page);

    long countDeliveries();

    long countDeliveriesBySenderOrReceiver(String user);

}
