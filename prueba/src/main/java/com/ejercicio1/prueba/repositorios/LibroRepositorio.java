/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio1.prueba.repositorios;

import com.ejercicio1.prueba.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro,Long> {
 
  
   
    @Query("Select l FROM Libro l WHERE l.titulo = :titulo")
  public Libro buscarPorTitulo(@Param("titulo") String titulo);   
  @Query("Select l FROM Libro l WHERE l.autor.nombre = :nombre")
  public List<Libro> buscarPorAutor(@Param ("nombre") String nombre);


}


