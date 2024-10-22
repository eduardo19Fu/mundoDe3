package com.aglayatech.mundo3.controller;

import com.aglayatech.mundo3.model.NotaCredito;
import com.aglayatech.mundo3.service.INotaCreditoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class NotaCreditoApiController {

    private final INotaCreditoService notaCreditoService;

    @GetMapping(value = "/notas-credito")
    public ResponseEntity<List<NotaCredito>> getDevolucions() {
        log.info("Retornando devoluciones");
        return ResponseEntity.ok(notaCreditoService.findAll());
    }

    @GetMapping(value = "/notas-credito/{id}")
    public ResponseEntity<NotaCredito> getDevolucionById(@PathVariable("id") Long id) {
        log.info("Retornando NotaCredito: {}", id);
        return ResponseEntity.ok(notaCreditoService.findNotaCredito(id));
    }

    @PostMapping(value = "/notas-credito")
    public ResponseEntity<NotaCredito> create(@RequestBody NotaCredito notaCredito) {
        log.info("Ejecutando notaCredito de productos");
        return ResponseEntity.status(HttpStatus.CREATED).body(notaCreditoService.save(notaCredito));
    }
}
