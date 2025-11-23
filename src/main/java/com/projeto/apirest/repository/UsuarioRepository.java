package com.projeto.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.apirest.model.Usuario;

//Metodos para CRUD e referenciando a tabela Usuarios
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    }

