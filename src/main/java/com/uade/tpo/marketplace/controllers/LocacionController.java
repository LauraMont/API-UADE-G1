package com.uade.tpo.marketplace.controllers;

import com.uade.tpo.marketplace.entity.Locacion;
import com.uade.tpo.marketplace.service.LocacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController
// @RequestMapping("/locaciones")
// public class LocacionController {

//     @Autowired
//     private LocacionService locacionService;

//     @GetMapping
//     public ResponseEntity<List<Locacion>> getAllLocaciones() {
//         return ResponseEntity.ok(locacionService.getAllLocaciones());
//     }
// } 