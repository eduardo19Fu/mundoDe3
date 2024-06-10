package com.aglayatech.mundo3.controller;

import com.aglayatech.mundo3.model.Caja;
import com.aglayatech.mundo3.service.ICajaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cajas")
@RequiredArgsConstructor
@Slf4j
public class CajaController {

    private final ICajaService cajaService;

    @GetMapping("/get")
    public ResponseEntity<List<Caja>> listarCajas() {
        log.info("Obteniendo lista de cajas");
        return ResponseEntity.ok(cajaService.getCajas());
    }

    @GetMapping("/filtro-fecha/get")
    public ResponseEntity<List<Caja>> listarCajas(@RequestParam("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha1,
                                                  @RequestParam("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha2)
    {
        log.info("Obteniendo lista de cajas aperturadas entre {} y {}", fecha1, fecha2);
        return ResponseEntity.ok(cajaService.getCajas(fecha1, fecha2));
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<Caja> buscarCaja(@PathVariable Long id) {
        log.info("Obteniendo Caja con ID: {}", id);
        return ResponseEntity.ok(cajaService.getCaja(id));
    }

    @PostMapping("/post")
    public ResponseEntity<Caja> crear(@RequestBody Caja caja) {
        log.info("*********** Aperturando nueva caja **************");
        return ResponseEntity.status(HttpStatus.CREATED).body(cajaService.guardar(caja));
    }

    @PutMapping("/put")
    public ResponseEntity<Caja> actualizar(@RequestBody Caja caja) {
        log.info("*********** Aperturando nueva caja **************");
        return ResponseEntity.ok(cajaService.guardar(caja));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("*********** Eliminando caja **************");
        cajaService.eliminarCaja(id);
        return ResponseEntity.noContent().build();
    }
}
