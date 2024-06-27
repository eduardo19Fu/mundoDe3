package com.aglayatech.mundo3.controller;

import com.aglayatech.mundo3.model.Caja;
import com.aglayatech.mundo3.model.MovimientoCaja;
import com.aglayatech.mundo3.service.ICajaService;
import com.aglayatech.mundo3.service.IMovimientoCajaService;

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
@RequestMapping(value = "/api/movimientos-caja")
@RequiredArgsConstructor
@Slf4j
public class MovimientoCajaApiController {

    private final IMovimientoCajaService movimientoCajaService;

    private final ICajaService cajaService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<MovimientoCaja>> listarMovimientos() {
        log.info("Retornando listado de movimientos de caja");
        return ResponseEntity.ok(movimientoCajaService.listarMovimientoCajas());
    }

    @GetMapping(value = "/movimientos/{idcaja}/get")
    public ResponseEntity<List<MovimientoCaja>> listarMovimientos(@PathVariable("idcaja") Long idcaja) {
        log.info("Buscando movimientos de la caja: {}", idcaja);
        Caja caja = cajaService.getCaja(idcaja);
        return ResponseEntity.ok(movimientoCajaService.listarMovimientoCajas(caja));
    }

    @GetMapping(value = "/movimiento/get/{id}")
    public ResponseEntity<MovimientoCaja> buscarMovimientoCaja(@PathVariable("id") Long id) {
        log.info("Buscando movimiento caja: {}", id);
        return ResponseEntity.ok(movimientoCajaService.buscarMovimiento(id));
    }

    @PostMapping(value = "/post")
    public ResponseEntity<MovimientoCaja> crearMovimientoCaja(@RequestBody MovimientoCaja movimientoCaja) {
        log.info("Creando nuevo movimiento de Caja");
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoCajaService.crearMovimientoCaja(movimientoCaja));
    }
}
