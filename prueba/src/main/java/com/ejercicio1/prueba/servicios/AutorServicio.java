/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio1.prueba.servicios;

import com.ejercicio1.prueba.entidades.Autor;
import com.ejercicio1.prueba.excepciones.MiExcepcion;
import com.ejercicio1.prueba.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class AutorServicio {
  @Autowired
  AutorRepositorio autorRepositorio;
    @Transactional
  public void crearAutor(String nombre) throws MiExcepcion{
   
      validar(nombre);
      Autor autor = new Autor();   
      autor.setNombre(nombre);
      
      autorRepositorio.save(autor);
  }  
  public List<Autor> listarAutores(){
        List<Autor> autores = new ArrayList();
        autores = autorRepositorio.findAll();
        return autores;
  

  }
@Transactional
public void modificarAutor(String nombre, String id) throws MiExcepcion {
    validar(nombre);

    Optional<Autor> respuestaAutor = autorRepositorio.findById(id);

    if (respuestaAutor.isPresent()) {
        Autor autor = respuestaAutor.get();
        autor.setNombre(nombre);
        autorRepositorio.save(autor);
    } else {
        throw new MiExcepcion("El autor con el ID proporcionado no existe");
    }
}

  @Transactional
  public Autor getOne(String id){
     return autorRepositorio.getOne(id);
  }
  public void validar(String nombre) throws MiExcepcion{
      if (nombre.isEmpty() || nombre == null) {
          throw new MiExcepcion("el nombre no puede estar vacio");
      }
  
      
  }
}
