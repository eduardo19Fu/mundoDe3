package com.aglayatech.mundo3.service;

import com.aglayatech.mundo3.model.Pais;

import java.util.List;

public interface IPaisService {

    public List<Pais> getAll();

    public Pais getPais(Integer idpais);

    public Pais save(Pais pais);
}
