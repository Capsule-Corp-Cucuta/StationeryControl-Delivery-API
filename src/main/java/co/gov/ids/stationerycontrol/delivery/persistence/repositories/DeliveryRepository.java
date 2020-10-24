package co.gov.ids.stationerycontrol.delivery.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;
import co.gov.ids.stationerycontrol.delivery.domain.dto.Delivery;
import co.gov.ids.stationerycontrol.delivery.domain.dto.DeliveryType;
import co.gov.ids.stationerycontrol.delivery.persistence.mapper.DeliveryMapper;
import co.gov.ids.stationerycontrol.delivery.domain.repository.IDeliveryRepository;

@Repository
public class DeliveryRepository implements IDeliveryRepository {

    private final int SIZE_PAGE = 25;
    private final DeliveryMapper mapper;
    private final IDeliveryJPARepository repository;

    public DeliveryRepository(DeliveryMapper mapper, IDeliveryJPARepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Delivery create(Delivery delivery) {
        return mapper.toDelivery(repository.save(mapper.toEntity(delivery)));
    }

    @Override
    public Optional<Delivery> findByTradeNumber(String tradeNumber) {
        return repository.findById(tradeNumber).map(entity -> mapper.toDelivery(entity));
    }

    @Override
    public Optional<List<Delivery>> findAll(int page) {
        return Optional.of(mapper.toDeliveries(repository.findAll(PageRequest.of(page, SIZE_PAGE)).getContent()));
    }

    @Override
    public Optional<List<Delivery>> findByDate(LocalDate date, int page) {
        return repository.findByDate(date, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toDeliveries(entities.getContent()));
    }

    @Override
    public Optional<List<Delivery>> findByDateBetween(LocalDate startDate, LocalDate endDate, int page) {
        return repository.findByDateBetween(startDate, endDate, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toDeliveries(entities.getContent()));
    }

    @Override
    public Optional<List<Delivery>> findByDeliveryType(DeliveryType type, int page) {
        return repository.findByDeliveryType(type, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toDeliveries(entities.getContent()));
    }

    @Override
    public Optional<List<Delivery>> findByDeliveryTypeAndSenderOrReceiver(DeliveryType type, String user, int page) {
        return repository.findByDeliveryTypeAndSenderOrReceiver(type, user, user, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toDeliveries(entities.getContent()));
    }

    @Override
    public Optional<List<Delivery>> findBySenderOrReceiver(String user, int page) {
        return repository.findBySenderOrReceiver(user, user, PageRequest.of(page, SIZE_PAGE))
                .map(entities -> mapper.toDeliveries(entities.getContent()));
    }
}
