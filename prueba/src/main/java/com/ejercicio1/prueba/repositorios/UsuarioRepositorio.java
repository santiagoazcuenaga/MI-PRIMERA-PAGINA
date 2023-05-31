/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio1.prueba.repositorios;

import com.ejercicio1.prueba.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {
   @Query ("Select u FROM Usuario u WHERE u.email = :email  ") 
  public Usuario buscarPorEmail(@Param("email")String email);  
    
}
