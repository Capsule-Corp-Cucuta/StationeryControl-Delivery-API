package co.gov.ids.stationerycontrol.delivery.application.services;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import co.gov.ids.stationerycontrol.delivery.domain.Delivery;
import org.springframework.transaction.annotation.Transactional;
import co.gov.ids.stationerycontrol.delivery.domain.DeliveryType;
import co.gov.ids.stationerycontrol.delivery.application.exceptions.NotFoundException;
import co.gov.ids.stationerycontrol.delivery.application.exceptions.BadRequestException;
import co.gov.ids.stationerycontrol.delivery.framework.persistence.mapper.DeliveryMapper;
import co.gov.ids.stationerycontrol.delivery.framework.persistence.entities.DeliveryEntity;
import co.gov.ids.stationerycontrol.delivery.framework.persistence.repositories.IDeliveryRepository;

/**
 * Class that implements IDeliveryService.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Service
@Transactional(readOnly = true)
public class DeliveryServiceImplementation implements IDeliveryService {

    private final int SIZE_PAGE = 25;
    private final IDeliveryRepository repository;

    public DeliveryServiceImplementation(IDeliveryRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delivery create(Delivery delivery) {
        DeliveryEntity entity = repository.findByTradeNumber(delivery.getTradeNumber());
        if (entity != null) {
            throw new BadRequestException("Delivery exist already");
        }
        delivery.setDate(new Date());

        return DeliveryMapper.toDomain(repository.save(DeliveryMapper.toEntity(delivery)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Delivery findByTradeNumber(String tradeNumber) {
        DeliveryEntity entity = repository.findByTradeNumber(tradeNumber);
        if (entity == null) {
            throw new NotFoundException("Delivery not found");
        }
        return DeliveryMapper.toDomain(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Delivery> findAll(int page) {
        List<Delivery> deliveries = new ArrayList<>();
        for (DeliveryEntity entity : repository.findAll()) {
            deliveries.add(DeliveryMapper.toDomain(entity));
        }
        if (deliveries.isEmpty()) {
            throw new NotFoundException("Deliveries not found");
        }
        return deliveries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Delivery> findByDate(Date date, int page) {
        List<Delivery> deliveries = new ArrayList<>();
        for (DeliveryEntity entity : repository.findByDate(date, PageRequest.of(page, SIZE_PAGE)).getContent()) {
            deliveries.add(DeliveryMapper.toDomain(entity));
        }
        if (deliveries.isEmpty()) {
            throw new NotFoundException("Deliveries not found");
        }
        return deliveries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Delivery> findByDateBetween(Date startDate, Date endDate, int page) {
        List<Delivery> deliveries = new ArrayList<>();
        for (DeliveryEntity entity : repository.findByDateBetween(startDate, endDate,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            deliveries.add(DeliveryMapper.toDomain(entity));
        }
        if (deliveries.isEmpty()) {
            throw new NotFoundException("Deliveries not found");
        }
        return deliveries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Delivery> findByDeliveryType(DeliveryType type, int page) {
        List<Delivery> deliveries = new ArrayList<>();
        for (DeliveryEntity entity : repository.findByDeliveryType(type,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            deliveries.add(DeliveryMapper.toDomain(entity));
        }
        if (deliveries.isEmpty()) {
            throw new NotFoundException("Deliveries not found");
        }
        return deliveries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Delivery> findByDeliveryTypeAndSenderOrReceiver(DeliveryType type, String user, int page) {
        List<Delivery> deliveries = new ArrayList<>();
        for (DeliveryEntity entity : repository.findByDeliveryTypeAndSenderOrReceiver(type, user, user,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            deliveries.add(DeliveryMapper.toDomain(entity));
        }
        if (deliveries.isEmpty()) {
            throw new NotFoundException("Deliveries not found");
        }
        return deliveries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Delivery> findBySenderOrReceiver(String user, int page) {
        List<Delivery> deliveries = new ArrayList<>();
        for (DeliveryEntity entity : repository.findBySenderOrReceiver(user, user,
                PageRequest.of(page, SIZE_PAGE)).getContent()) {
            deliveries.add(DeliveryMapper.toDomain(entity));
        }
        if (deliveries.isEmpty()) {
            throw new NotFoundException("Deliveries not found");
        }
        return deliveries;
    }
}
