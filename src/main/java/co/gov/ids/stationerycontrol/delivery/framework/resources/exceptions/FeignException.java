package co.gov.ids.stationerycontrol.delivery.framework.resources.exceptions;

/**
 * Class to describe a Feign exception.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public class FeignException extends RuntimeException {

    public FeignException(String message) {
        super(message);
    }

}
