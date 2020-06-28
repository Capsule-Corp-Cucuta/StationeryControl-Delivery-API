package co.gov.ids.stationerycontrol.delivery.application.services;

import java.util.Date;
import java.util.List;

import co.gov.ids.stationerycontrol.delivery.domain.Delivery;
import co.gov.ids.stationerycontrol.delivery.domain.DeliveryType;

/**
 * Interface that represents the use cases of Deliveries.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public interface IDeliveryService {

    /**
     * Function to create a new Delivery.
     *
     * @param delivery Delivery to will be created.
     * @return Delivery created.
     */
    Delivery create(Delivery delivery);

    /**
     * Function to find a Delivery by trade number.
     *
     * @param tradeNumber number to identify Deliveries.
     * @return Delivery identified by trade number.
     */
    Delivery findByTradeNumber(String tradeNumber);

    /**
     * Function to find all Deliveries.
     *
     * @param page number of page to list.
     * @return List of deliveries by page.
     */
    List<Delivery> findAll(int page);

    /**
     * Function to find deliveries by date.
     *
     * @param date date to consult deliveries.
     * @param page number of page to list.
     * @return List of deliveries by a date.
     */
    List<Delivery> findByDate(Date date, int page);

    /**
     * Function to find deliveries between two dates.
     *
     * @param startDate date of begin to search.
     * @param endDate   date of end to search.
     * @param page      number of page to list.
     * @return List of deliveries between two dates.
     */
    List<Delivery> findByDateBetween(Date startDate, Date endDate, int page);

    /**
     * Function to find deliveries by type of delivery.
     *
     * @param type type of delivery.
     * @param page number of page to list.
     * @return List of deliveries by type.
     */
    List<Delivery> findByDeliveryType(DeliveryType type, int page);

    /**
     * Function to find deliveries by type of delivery and sender or receiver.
     *
     * @param type type of delivery.
     * @param user User as sender or receiver of the deliveries.
     * @param page number of page to list.
     * @return List of deliveries by type and user.
     */
    List<Delivery> findByDeliveryTypeAndSenderOrReceiver(DeliveryType type, String user, int page);

    /**
     * Function to find deliveries by sender or receiver.
     *
     * @param user User as sender or receiver of the deliveries.
     * @param page number of page to list.
     * @return List of deliveries by user.
     */
    List<Delivery> findBySenderOrReceiver(String user, int page);

}
