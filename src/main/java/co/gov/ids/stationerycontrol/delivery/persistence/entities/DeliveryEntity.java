package co.gov.ids.stationerycontrol.delivery.persistence.entities;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import co.gov.ids.stationerycontrol.delivery.domain.dto.DeliveryType;

@Data
@Entity
@Table(name = "DELIVERIES")
public class DeliveryEntity {

    @Id
    @Column(name = "trade_number")
    private String tradeNumber;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type", nullable = false)
    private DeliveryType deliveryType;

    @Column(name = "first_certificate", nullable = false)
    private int firstCertificate;

    @Column(name = "last_certificate", nullable = false)
    private int lastCertificate;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;

}
