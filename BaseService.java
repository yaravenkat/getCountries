package com.api.country;

import java.math.BigDecimal;
import java.util.function.Predicate;

import org.apache.commons.validator.routines.InetAddressValidator;

import com.api.country.exception.ValidationException;

public interface BaseService {

	interface Params {
		String API = "/api";
		String V1 = "/v1";
		String BASE_PATH = API + V1;
		String NORTH_COUNTRIES = "/northcountries";
		int MIN_ALLOWED_IPS = 1;
		int MAX_ALLOWED_IPS = 5;
		//String API_IP_VIGILANTE = "https://ipvigilante.com/json/%s";
		String API_IP_VIGILANTE = "https://ipinfo.io/%s";
	}

	interface Predicates {
		Predicate<Object> CHECK_NOT_NULL_RETURN_TRUE = val -> val != null;
		Predicate<String[]> CHECK_IF_IPS_IS_GREATER_THAN_0_LESS_THAN_5 = (input) -> {
			if (CHECK_NOT_NULL_RETURN_TRUE.test(input) && input.length >= Params.MIN_ALLOWED_IPS
					&& input.length <= Params.MAX_ALLOWED_IPS) {
				return true;
			} else {
				throw new ValidationException(
						"Minimumm: " + Params.MIN_ALLOWED_IPS + " Maximumm:" + Params.MAX_ALLOWED_IPS);
			}
		};
		Predicate<BigDecimal> CHECKIFLATITUDEINNORTHERN = (input) -> (CHECK_NOT_NULL_RETURN_TRUE.test(input)
				&& input.compareTo(BigDecimal.ZERO) >= 0 && input.compareTo(BigDecimal.valueOf(90)) <= 0);
		Predicate<String> VALID_IP_ADDRESS = (input) -> InetAddressValidator.getInstance().isValid(input);
	}

}
