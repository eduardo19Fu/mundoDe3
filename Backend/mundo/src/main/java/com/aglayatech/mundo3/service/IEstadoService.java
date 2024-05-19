package com.aglayatech.mundo3.service;

import java.util.List;

import com.aglayatech.mundo3.model.Estado;

public interface IEstadoService {
	
	public List<Estado> findAll();
	
	public Estado findById(Integer idestado);
	
	public Estado findByEstado(String estado);
	
	public Estado save(Estado estado);
	
	public void delete(Integer idestado);

}
