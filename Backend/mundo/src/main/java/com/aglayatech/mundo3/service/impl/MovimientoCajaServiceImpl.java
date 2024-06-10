package com.aglayatech.mundo3.service.impl;

import com.aglayatech.mundo3.error.exceptions.DataAccessException;
import com.aglayatech.mundo3.model.MovimientoCaja;
import com.aglayatech.mundo3.repository.IMovimientoCajaRepository;
import com.aglayatech.mundo3.service.IMovimientoCajaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovimientoCajaServiceImpl implements IMovimientoCajaService {

    private final IMovimientoCajaRepository movimientoCajaRepository;

    @Override
    public List<MovimientoCaja> listarMovimientoCajas() {
        String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
        log.debug("Enter {}", __method);

        try {

        } catch (DataAccessException e) {

        }
        return null;
    }

    @Override
    public List<MovimientoCaja> listarMovimientoCajas(LocalDate fecha1, LocalDate fecha2) {
        return null;
    }

    @Override
    public MovimientoCaja crearMovimientoCaja(MovimientoCaja movimientoCaja) {
        return null;
    }

    @Override
    public MovimientoCaja actualizarMovimientoCaja(MovimientoCaja movimientoCaja) {
        return null;
    }

    @Override
    public void eliminarMovimientoCaja(MovimientoCaja movimientoCaja) {

    }
}
