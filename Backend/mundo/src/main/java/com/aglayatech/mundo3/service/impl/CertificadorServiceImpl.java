package com.aglayatech.mundo3.service.impl;

import com.aglayatech.mundo3.model.Certificador;
import com.aglayatech.mundo3.repository.ICertificadorRepository;
import com.aglayatech.mundo3.service.ICertificadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificadorServiceImpl implements ICertificadorService {

    @Autowired
    private ICertificadorRepository certificadorRepository;

    @Override
    public Certificador getCertificador(Integer idcertificador) {
        return this.certificadorRepository.findById(idcertificador).orElse(null);
    }
}
