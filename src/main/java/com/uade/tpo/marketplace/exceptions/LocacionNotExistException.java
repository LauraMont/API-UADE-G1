package com.uade.tpo.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Locacion no existe")
public class LocacionNotExistException extends Exception {

}
