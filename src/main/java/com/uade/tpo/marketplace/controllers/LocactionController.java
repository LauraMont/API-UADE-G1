package com.uade.tpo.marketplace.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.dto.LocacionRequest;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.exceptions.LocacionDuplicadaException;
import com.uade.tpo.marketplace.service.LocacionService;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/locacion")
public class LocactionController {
     @Autowired
    private LocacionService locacionService;
    @PostMapping
    public ResponseEntity<LocacionRequest> createLocation(@RequestBody LocacionRequest locacionRequest) throws CategoryDuplicateException, LocacionDuplicadaException {
        LocacionRequest result = locacionService.createLocacion(locacionRequest.getNombre(), locacionRequest.getDireccion(), locacionRequest.getZonas());
        return ResponseEntity.created(URI.create("/locacion/")).body(result);
    }
    
    
}
