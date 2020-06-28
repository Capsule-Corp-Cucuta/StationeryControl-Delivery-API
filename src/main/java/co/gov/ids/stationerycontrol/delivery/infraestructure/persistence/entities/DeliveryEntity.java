package co.gov.ids.stationerycontrol.delivery.infraestructure.persistence.entities;

import lombok.Data;
import java.util.Date;
import javax.persistence.*;

import co.gov.ids.stationerycontrol.delivery.domain.DeliveryType;

/**
 * Class to represent Deliveries for persist in DB.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Data
@Entity
@Table(name = "delivery")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "trade_number", nullable = false)
    private String tradeNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type", nullable = false)
    private DeliveryType deliveryType;

    @Column(name = "first_certificate", nullable = false)
    private int firstCertificate;

    @Column(name = "last_certificate", nullable = false)
    private int lastCertificate;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "receiver", nullable = false)
    private String receiver;

}
