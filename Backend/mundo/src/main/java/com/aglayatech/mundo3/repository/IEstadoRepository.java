package com.aglayatech.mundo3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aglayatech.mundo3.model.Estado;

public interface IEstadoRepository extends JpaRepository<Estado, Integer> {
	
	Optional<Estado> findByEstado(String estado);

}
