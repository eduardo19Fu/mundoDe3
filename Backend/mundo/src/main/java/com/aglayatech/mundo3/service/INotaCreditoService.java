package com.aglayatech.mundo3.service;

import com.aglayatech.mundo3.model.NotaCredito;

import java.util.List;

public interface INotaCreditoService {

    public List<NotaCredito> findAll();

    public NotaCredito findNotaCredito(Long id);

    public NotaCredito save(NotaCredito notaCredito);

    public void delete(NotaCredito notaCredito);

}
