package co.gov.ids.stationerycontrol.delivery.framework.persistence.repositories;

import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import co.gov.ids.stationerycontrol.delivery.domain.DeliveryType;
import co.gov.ids.stationerycontrol.delivery.framework.persistence.entities.DeliveryEntity;

/**
 * Interface that define the available operations to DB.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public interface IDeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

    /**
     * Function to find a Delivery by trade number.
     *
     * @param tradeNumber number to identify a Delivery.
     * @return Delivery identified by trade number.
     */
    DeliveryEntity findByTradeNumber(String tradeNumber);

    /**
     * Function to list deliveries by a date.
     *
     * @param date date to consult deliveries.
     * @param page number of page to list.
     * @return List of deliveries by date.
     */
    Page<DeliveryEntity> findByDate(Date date, Pageable page);

    /**
     * Function to list deliveries between two dates.
     *
     * @param startDate date to start to search.
     * @param endDate   date to end the search.
     * @param page      number of page to list.
     * @return List of deliveries by date.
     */
    Page<DeliveryEntity> findByDateBetween(Date startDate, Date endDate, Pageable page);

    /**
     * Function to list deliveries by type of celivery.
     *
     * @param type type of delivery.
     * @param page number of page to list.
     * @return List of deliveries by type.
     */
    Page<DeliveryEntity> findByDeliveryType(DeliveryType type, Pageable page);

    /**
     * Function to list deliveries by type and user.
     *
     * @param type     type of delivery.
     * @param sender   User who send.
     * @param receiver User who receive.
     * @param page     number of page to list.
     * @return List of deliveries by type and user.
     */
    Page<DeliveryEntity> findByDeliveryTypeAndSenderOrReceiver(DeliveryType type, String sender,
                                                               String receiver, Pageable page);

    /**
     * Function to list deliveries by user.
     *
     * @param sender   User who send.
     * @param receiver User who receive.
     * @param page     number of page to list.
     * @return List of deliveries by user.
     */
    Page<DeliveryEntity> findBySenderOrReceiver(String sender, String receiver, Pageable page);

}
