package com.aglayatech.mundo3.service.impl;

import com.aglayatech.mundo3.model.Compra;
import com.aglayatech.mundo3.model.TipoComprobante;
import com.aglayatech.mundo3.repository.ICompraRepository;
import com.aglayatech.mundo3.service.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements ICompraService {

    @Autowired
    private ICompraRepository compraRepository;

    @Override
    public List<Compra> getAll() {
        return this.compraRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaRegistro"));
    }

    @Override
    public Compra getCompra(Long idcompra) {
        return this.compraRepository.findById(idcompra).orElse(null);
    }

    @Override
    public Compra save(Compra compra) {
        return this.compraRepository.save(compra);
    }

    @Override
    public void delete(Long idcompra) {
        this.compraRepository.deleteById(idcompra);
    }

    @Override
    public List<TipoComprobante> getTipos() {
        return this.compraRepository.findtipos();
    }
}
