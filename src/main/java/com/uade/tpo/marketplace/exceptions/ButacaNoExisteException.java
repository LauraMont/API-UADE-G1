package com.uade.tpo.marketplace.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La butaca no existe")
public class ButacaNoExisteException extends Exception{
    
}
