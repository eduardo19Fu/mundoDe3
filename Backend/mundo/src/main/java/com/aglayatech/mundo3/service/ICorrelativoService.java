package com.aglayatech.mundo3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aglayatech.mundo3.model.Correlativo;
import com.aglayatech.mundo3.model.Estado;
import com.aglayatech.mundo3.model.Usuario;

public interface ICorrelativoService {
	
	public List<Correlativo> findAll();
	
	public Page<Correlativo> findAll(Pageable pageable);
	
	public Correlativo findById(Long idcorrelativo);
	
	public Correlativo findByUsuario(Usuario usuario, Estado estado);
	
	public Correlativo save(Correlativo correlativo);
	
	public void delete(Long id);

}
