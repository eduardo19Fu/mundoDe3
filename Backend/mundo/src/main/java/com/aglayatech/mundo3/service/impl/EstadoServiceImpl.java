package com.aglayatech.mundo3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aglayatech.mundo3.model.Estado;
import com.aglayatech.mundo3.repository.IEstadoRepository;
import com.aglayatech.mundo3.service.IEstadoService;

@Service
public class EstadoServiceImpl implements IEstadoService {

	@Autowired
	private IEstadoRepository repoEstado;
	
	@Override
	public List<Estado> findAll() {
		return repoEstado.findAll(Sort.by(Direction.ASC, "idEstado"));
	}

	@Override
	public Estado findById(Integer idestado) {
		return repoEstado.findById(idestado).orElse(null);
	}
	
	@Override
	public Estado findByEstado(String estado) {
		return repoEstado.findByEstado(estado).orElse(null);
	}

	@Override
	public Estado save(Estado estado) {
		return repoEstado.save(estado);
	}

	@Override
	public void delete(Integer idestado) {
		repoEstado.deleteById(idestado);
	}

}
