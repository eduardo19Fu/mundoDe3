package com.aglayatech.mundo3.service;

import com.aglayatech.mundo3.model.Caja;

import java.time.LocalDate;
import java.util.List;

public interface ICajaService {

    public List<Caja> getCajas();

    public List<Caja> getCajas(LocalDate fechaIni, LocalDate fechaFin);

    public Caja getCajaByIdUsuario(Integer idusuario, String estado);

    public Caja getCaja(Long idcaja);

    public Caja guardar(Caja caja);

    public void eliminarCaja(Long idcaja);
}
