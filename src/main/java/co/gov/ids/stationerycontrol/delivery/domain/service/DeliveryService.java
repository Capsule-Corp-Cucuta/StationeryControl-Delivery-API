package co.gov.ids.stationerycontrol.delivery.domain.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import co.gov.ids.stationerycontrol.delivery.domain.dto.Delivery;
import co.gov.ids.stationerycontrol.delivery.domain.dto.DeliveryType;
import co.gov.ids.stationerycontrol.delivery.domain.repository.IDeliveryRepository;

@Service
public class DeliveryService {

    private final IDeliveryRepository repository;

    public DeliveryService(IDeliveryRepository repository) {
        this.repository = repository;
    }

    public Delivery create(Delivery delivery) {
        delivery.setDate(LocalDate.now());
        return repository.create(delivery);
    }

    public Optional<Delivery> findByTradeNumber(String tradeNumber) {
        return repository.findByTradeNumber(tradeNumber);
    }

    public Optional<List<Delivery>> findAll(int page) {
        return repository.findAll(page);
    }

    public Optional<List<Delivery>> findByDate(LocalDate date, int page) {
        return repository.findByDate(date, page);
    }

    public Optional<List<Delivery>> findByDateBetween(LocalDate startDate, LocalDate endDate, int page) {
        return repository.findByDateBetween(startDate, endDate, page);
    }

    public Optional<List<Delivery>> findByDeliveryType(DeliveryType type, int page) {
        return repository.findByDeliveryType(type, page);
    }

    public Optional<List<Delivery>> findByDeliveryTypeAndSenderOrReceiver(DeliveryType type, String user, int page) {
        return repository.findByDeliveryTypeAndSenderOrReceiver(type, user, page);
    }

    public Optional<List<Delivery>> findBySenderOrReceiver(String user, int page) {
        return repository.findBySenderOrReceiver(user, page);
    }

    public long countDeliveries() {
        return repository.countDeliveries();
    }

    public long countDeliveriesBySenderOrReceiver(String user) {
        return repository.countDeliveriesBySenderOrReceiver(user);
    }
}
