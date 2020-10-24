package co.gov.ids.stationerycontrol.delivery.domain.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Delivery {

    private String tradeNumber;
    private LocalDate date;
    private DeliveryType deliveryType;
    private int firstCertificate;
    private int lastCertificate;
    private String sender;
    private String receiver;

}
