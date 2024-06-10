package com.aglayatech.mundo3.service;

import com.aglayatech.mundo3.model.MovimientoCaja;

import java.time.LocalDate;
import java.util.List;

public interface IMovimientoCajaService {

    public List<MovimientoCaja> listarMovimientoCajas();

    public List<MovimientoCaja> listarMovimientoCajas(LocalDate fecha1, LocalDate fecha2);

    public MovimientoCaja crearMovimientoCaja(MovimientoCaja movimientoCaja);

    public MovimientoCaja actualizarMovimientoCaja(MovimientoCaja movimientoCaja);

    public void eliminarMovimientoCaja(MovimientoCaja movimientoCaja);
}
