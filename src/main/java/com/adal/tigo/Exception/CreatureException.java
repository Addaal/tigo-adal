package com.adal.tigo.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreatureException extends Exception {

    private final String timestamp;
    private final String code;
    private final String details;
    private final int httpStatus;


}
