package com.aglayatech.mundo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aglayatech.mundo3.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer>{
	
	@Query("Select r from Role r where r.role = :role")
	Role getRoles(@Param("role") String role);

}
