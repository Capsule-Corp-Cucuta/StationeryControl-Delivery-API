package co.gov.ids.stationerycontrol.delivery.web.controller;

import java.util.List;
import java.time.LocalDate;
import java.text.ParseException;

import io.swagger.annotations.Api;

import java.time.format.DateTimeFormatter;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.gov.ids.stationerycontrol.delivery.domain.dto.Delivery;
import co.gov.ids.stationerycontrol.delivery.domain.dto.DeliveryType;
import co.gov.ids.stationerycontrol.delivery.web.feign.ICertificateFeign;
import co.gov.ids.stationerycontrol.delivery.domain.service.DeliveryService;

@RestController
@Api(tags = "delivery")
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService service;
    private final ICertificateFeign certificateFeign;

    public DeliveryController(DeliveryService service, ICertificateFeign certificateFeign) {
        this.service = service;
        this.certificateFeign = certificateFeign;
    }

    @PostMapping
    @ApiOperation(value = "Post a Delivery", notes = "Service for create a new delivery")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Delivery was created correctly"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<Delivery> create(@RequestBody Delivery delivery) {
        ResponseEntity certificatesResponse = certificateFeign.findByNumberBetween(delivery.getFirstCertificate(),
                delivery.getLastCertificate());
        if (certificatesResponse.getStatusCodeValue() == 200) {
            certificateFeign.updateMultipleCertificates(delivery.getFirstCertificate(),
                    delivery.getLastCertificate(),
                    delivery.getReceiver());
            return new ResponseEntity<>(service.create(delivery), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{tradeNumber}")
    @ApiOperation(value = "Get a delivery by trade number", notes = "Service for get a delivery by trade number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delivery was find correctly"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<Delivery> findByTradeNumber(@PathVariable("tradeNumber") String tradeNumber) {
        return service.findByTradeNumber(tradeNumber)
                .map(delivery -> new ResponseEntity<>(delivery, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all/{page}")
    @ApiOperation(value = "Get all deliveries", notes = "Service for get all deliveries")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Delivery>> findAll(@PathVariable("page") int page) {
        return service.findAll(page)
                .map(deliveries -> new ResponseEntity<>(deliveries, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/date/{page}")
    @ApiOperation(value = "Get deliveries by a date", notes = "Service for get a list of deliveries by a date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deliveries were listed"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Delivery>> findByDate(@PathVariable("page") int page, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return service.findByDate(localDate, page)
                .map(deliveries -> new ResponseEntity<>(deliveries, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/between-dates/{page}")
    @ApiOperation(
            value = "Get deliveries between two dates",
            notes = "Service for get a list of deliveries between two dates"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Delivery>> findByDateBetween(@PathVariable("page") int page,
                                                            String startDate, String endDate) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateStart = LocalDate.parse(startDate, formatter);
        LocalDate localDateEnd = LocalDate.parse(endDate, formatter);
        return service.findByDateBetween(localDateStart, localDateEnd, page)
                .map(deliveries -> new ResponseEntity<>(deliveries, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}/{page}")
    @ApiOperation(value = "Get deliveries by type", notes = "Service for get a list of deliveries by delivery type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Delivery>> findByDeliveryType(@PathVariable("type") DeliveryType type,
                                                             @PathVariable("page") int page) {
        return service.findByDeliveryType(type, page)
                .map(deliveries -> new ResponseEntity<>(deliveries, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}/user/{user}/{page}")
    @ApiOperation(
            value = "Get deliveries by type and User",
            notes = "Service for get a list of deliveries by delivery type and User as receiver or sender"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Delivery>> findByDeliveryTypeAndSenderOrReceiver(@PathVariable("type") DeliveryType type,
                                                                                @PathVariable("user") String user,
                                                                                @PathVariable("page") int page) {
        return service.findByDeliveryTypeAndSenderOrReceiver(type, user, page)
                .map(deliveries -> new ResponseEntity<>(deliveries, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{user}/{page}")
    @ApiOperation(
            value = "Get deliveries by User",
            notes = "Service for get a list of deliveries by User as receiver or sender"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deliveries were listed correctly"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<List<Delivery>> findBySenderOrReceiver(@PathVariable("user") String user,
                                                                 @PathVariable("page") int page) {
        return service.findBySenderOrReceiver(user, page)
                .map(deliveries -> new ResponseEntity<>(deliveries, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
