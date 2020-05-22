package co.gov.ids.stationerycontrol.delivery.domain;

import lombok.Data;
import java.util.Date;


/**
 * Class to represent Deliveries.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Data
public class Delivery {

    private String tradeNumber;
    private Date date;
    private DeliveryType deliveryType;
    private int firstCertificate;
    private int lastCertificate;
    private String sender;
    private String receiver;

}
