package com.aglayatech.mundo3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aglayatech.mundo3.model.Correlativo;
import com.aglayatech.mundo3.model.Estado;
import com.aglayatech.mundo3.model.Usuario;

public interface ICorrelativoRepository extends JpaRepository<Correlativo, Long> {
	
	// Buscar el correlativo del usuario logueado en el sistema
	public Optional<Correlativo> findByUsuarioAndEstado(Usuario usuario, Estado estado);

}
