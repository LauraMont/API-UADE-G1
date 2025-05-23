package com.uade.tpo.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<String> handleUserNotExist(UserNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado: " + ex.getMessage());
    }

    @ExceptionHandler(StockMaxReached.class)
    public ResponseEntity<String> handleStockMaxReached(StockMaxReached ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No hay suficiente stock: " + ex.getMessage());
    }

    @ExceptionHandler(LocacionNotExistException.class)
    public ResponseEntity<String> handleLocacionNotExist(LocacionNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locación no encontrada: " + ex.getMessage());
    }

    @ExceptionHandler(EventNotExistException.class)
    public ResponseEntity<String> handleEventNotExist(EventNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado: " + ex.getMessage());
    }

    @ExceptionHandler(EventDuplicateException.class)
    public ResponseEntity<String> handleEventDuplicate(EventDuplicateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El evento ya existe: " + ex.getMessage());
    }

    @ExceptionHandler(CategoryDuplicateException.class)
    public ResponseEntity<String> handleCategoryDuplicate(CategoryDuplicateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("La categoría ya existe: " + ex.getMessage());
    }

    @ExceptionHandler(ArtistaNotExistException.class)
    public ResponseEntity<String> handleArtistaNotExist(ArtistaNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado: " + ex.getMessage());
    }

    // Catch-all para errores no controlados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllOthers(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error inesperado: " + ex.getMessage());
    }
}
