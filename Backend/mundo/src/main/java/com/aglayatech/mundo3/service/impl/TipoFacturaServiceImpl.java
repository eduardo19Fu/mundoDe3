package com.aglayatech.mundo3.service.impl;

import com.aglayatech.mundo3.model.TipoFactura;
import com.aglayatech.mundo3.repository.ITipoFacturaRepository;
import com.aglayatech.mundo3.service.ITipoFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoFacturaServiceImpl implements ITipoFacturaService {

    @Autowired
    private ITipoFacturaRepository tipoFacturaRepository;

    @Override
    public TipoFactura getTipoFactura(Integer id) {
        return this.tipoFacturaRepository.findById(id).orElse(null);
    }
}
