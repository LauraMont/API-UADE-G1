package com.uade.tpo.marketplace.exceptions;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Locacion no existe")
public class LocacionDuplicadaException extends Exception {
}
