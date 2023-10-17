package com.projetoExemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoExemplo.entities.Usuario;

	public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
		
	}

