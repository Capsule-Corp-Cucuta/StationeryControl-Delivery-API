package co.gov.ids.stationerycontrol.delivery.web.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "STATIONERYCONTROL-CERTIFICATE-API")
public interface ICertificateFeign {

    @GetMapping("/api/certificate/between/{startNumber}-{endNumber}")
    ResponseEntity findByNumberBetween(@PathVariable("startNumber") int startNumber,
                                       @PathVariable("endNumber") int endNumber);

    @PutMapping("/api/certificate/multiple/{startNumber}-{endNumber}/attendant/{attendant}")
    ResponseEntity updateMultiple(@PathVariable("startNumber") int startNumber,
                                  @PathVariable("endNumber") int endNumber,
                                  @PathVariable("attendant") String attendant);

}
