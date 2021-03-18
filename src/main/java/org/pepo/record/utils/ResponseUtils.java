package org.pepo.record.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ResponseUtils {

    public ResponseUtils() {
    }

    public static <T>ResponseEntity<T> createResponse(final T value, final HttpStatus httpStatus) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        return new ResponseEntity<T>(value, headers, httpStatus);
    }
}
