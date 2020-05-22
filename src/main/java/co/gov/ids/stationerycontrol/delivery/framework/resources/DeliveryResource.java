package co.gov.ids.stationerycontrol.delivery.framework.resources;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.gov.ids.stationerycontrol.delivery.domain.Delivery;
import co.gov.ids.stationerycontrol.delivery.domain.DeliveryType;
import co.gov.ids.stationerycontrol.delivery.framework.feigns.ICertificateFeign;
import co.gov.ids.stationerycontrol.delivery.application.services.IDeliveryService;
import co.gov.ids.stationerycontrol.delivery.framework.resources.exceptions.FeignException;

/**
 * Class to represents the web service of certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@RestController
@Api(tags = "delivery")
@RequestMapping("/api/delivery")
public class DeliveryResource {

    private final IDeliveryService service;
    private final ICertificateFeign feignClient;

    public DeliveryResource(IDeliveryService service, ICertificateFeign feignClient) {
        this.service = service;
        this.feignClient = feignClient;
    }

    /**
     * POST Method to create a Delivery.
     *
     * @param delivery Delivery to will be persist.
     * @return Delivery created, code 201.
     */
    @PostMapping
    @ApiOperation(value = "Create a Delivery", notes = "Service for create a new delivery")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Delivery was created correctly"),
            @ApiResponse(code = 400, message = "Invalid request")})
    public ResponseEntity<Delivery> create(@RequestBody Delivery delivery) {
        ResponseEntity certificatesResponse = feignClient.findByNumberBetween(delivery.getFirstCertificate(),
                delivery.getLastCertificate());
        if (certificatesResponse.getStatusCodeValue() == 200) {
            feignClient.updateMultipleCertificates(delivery.getFirstCertificate(),
                    delivery.getLastCertificate(),
                    delivery.getReceiver());
            return new ResponseEntity<>(service.create(delivery), HttpStatus.CREATED);
        }
        throw new FeignException("Had an unexpected error with Certificates API");
    }

    /**
     * GET Method to find a delivery by trade number.
     *
     * @param tradeNumber number to identify a delivery.
     * @return Delivery identified by trade number, code 200.
     */
    @GetMapping("/{tradeNumber}")
    @ApiOperation(value = "Get a delivery by trade number", notes = "Service for get a delivery by trade number")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Delivery was find correctly"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<Delivery> findByTradeNumber(@PathVariable("tradeNumber") String tradeNumber) {
        return new ResponseEntity<>(service.findByTradeNumber(tradeNumber), HttpStatus.OK);
    }


    /**
     * GET Method to list all deliveries.
     *
     * @param page number of page to list.
     * @return List of all deliveries, code 200.
     */
    @GetMapping("/all/{page}")
    @ApiOperation(value = "Get all deliveries", notes = "Service for get all deliveries")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Delivery>> findAll(@PathVariable("page") int page) {
        return new ResponseEntity<>(service.findAll(page), HttpStatus.OK);
    }

    /**
     * GET Method to list deliveries by a date.
     *
     * @param page number of page to list.
     * @param date date of deliveries to search.
     * @return List fo deliveries by a date, code 200.
     * @throws ParseException
     */
    @GetMapping("/date/{page}")
    @ApiOperation(value = "Get deliveries by a date", notes = "Service for get a list of deliveries by a date")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deliveries were listed"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Delivery>> findByDate(@PathVariable("page") int page,
                                                     String date) throws ParseException {
        Date convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return new ResponseEntity<>(service.findByDate(convertedDate, page), HttpStatus.OK);
    }

    /**
     * GET Method to lis deliveries between two dates.
     *
     * @param page      number of page to list.
     * @param startDate date to start to find.
     * @param endDate   date to end to find.
     * @return List of deliveries between two dates, code 200.
     * @throws ParseException
     */
    @GetMapping("/between-dates/{page}")
    @ApiOperation(value = "Get deliveries between two dates",
            notes = "Service for get a list of deliveries between two dates")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Delivery>> findByDateBetween(@PathVariable("page") int page,
                                                            String startDate, String endDate) throws ParseException {
        Date convertedStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date convertedEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        return new ResponseEntity<>(service.findByDateBetween(convertedStartDate, convertedEndDate, page),
                HttpStatus.OK);
    }

    /**
     * GET Method to list deliveries by delivery type.
     *
     * @param type type of delivery to list.
     * @param page number of page to list.
     * @return List of deliveries by delivery type.
     */
    @GetMapping("/type/{type}/{page}")
    @ApiOperation(value = "Get deliveries by type", notes = "Service for get a list of deliveries by delivery type")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Delivery>> findByDeliveryType(@PathVariable("type") DeliveryType type,
                                                             @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByDeliveryType(type, page), HttpStatus.OK);
    }

    /**
     * GET Method to list deliveries by delivery type and User as receiver or sender.
     *
     * @param type type of delivery to list.
     * @param user user as receiver or sender.
     * @param page number of page to list.
     * @return List of deliveries by type and User.
     */
    @GetMapping("/type/{type}/user/{user}/{page}")
    @ApiOperation(value = "Get deliveries by type and User",
            notes = "Service for get a list of deliveries by delivery type and User as receiver or sender")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Delivery>> findByDeliveryTypeAndSenderOrReceiver(@PathVariable("type") DeliveryType type,
                                                                                @PathVariable("user") String user,
                                                                                @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findByDeliveryTypeAndSenderOrReceiver(type, user, page), HttpStatus.OK);
    }

    /**
     * GET Method to list deliveries by User.
     *
     * @param user User as receiver or sender.
     * @param page number of page to list.
     * @return List of deliveries by User.
     */
    @GetMapping("/user/{user}/{page}")
    @ApiOperation(value = "Get deliveries by User",
            notes = "Service for get a list of deliveries by User as receiver or sender")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 400, message = "Invalid request"), @ApiResponse(code = 404, message = "Not found")})
    public ResponseEntity<List<Delivery>> findBySenderOrReceiver(@PathVariable("user") String user,
                                                                 @PathVariable("page") int page) {
        return new ResponseEntity<>(service.findBySenderOrReceiver(user, page), HttpStatus.OK);
    }

}
