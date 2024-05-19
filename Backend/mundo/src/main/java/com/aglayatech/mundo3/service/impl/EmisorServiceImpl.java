package com.aglayatech.mundo3.service.impl;

import com.aglayatech.mundo3.model.Emisor;
import com.aglayatech.mundo3.repository.IEmisorRepository;
import com.aglayatech.mundo3.service.IEmisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmisorServiceImpl implements IEmisorService {

    @Autowired
    private IEmisorRepository emisorRepository;

    @Override
    public Emisor getEmisor(Integer idemisor) {
        return this.emisorRepository.findById(idemisor).orElse(null);
    }
}
