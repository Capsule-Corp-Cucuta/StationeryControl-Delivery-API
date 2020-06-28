package co.gov.ids.stationerycontrol.delivery.infraestructure.feigns;

import org.springframework.http.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface that represents the feign client of Certificates.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@FeignClient(name = "STATIONERYCONTROL-CERTIFICATE-API")
public interface ICertificateFeign {

    /**
     * Function to find Certificates between two numbers of certificates.
     *
     * @param startNumber number to start to search.
     * @param endNumber   number to end the search.
     * @return status code of resource.
     */
    @GetMapping("/api/certificate/between/{startNumber}-{endNumber}")
    ResponseEntity findByNumberBetween(@PathVariable("startNumber") int startNumber,
                                       @PathVariable("endNumber") int endNumber);

    /**
     * Function to update the attendant and state of certificates between two numbers.
     *
     * @param startNumber number to start to search.
     * @param endNumber   number to end the search.
     * @param attendant   new attendant of the certificates.
     * @return status code of resource.
     */
    @PutMapping("/api/certificate/multiple/{startNumber}-{endNumber}/attendant/{attendant}")
    ResponseEntity updateMultipleCertificates(@PathVariable("startNumber") int startNumber,
                                              @PathVariable("endNumber") int endNumber,
                                              @PathVariable("attendant") String attendant);

}
