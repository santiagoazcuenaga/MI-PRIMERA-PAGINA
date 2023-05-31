/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio1.prueba.servicios;

import com.ejercicio1.prueba.entidades.Editorial;
import com.ejercicio1.prueba.repositorios.EditorialRepositorio;
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
public class EditorialServicio {
    @Autowired
    EditorialRepositorio editorialRepositorio;
    @Transactional
   public void crearEditorial(String nombre){
       Editorial editorial = new Editorial();
       editorial.setNombre(nombre);
       editorialRepositorio.save(editorial);
       
       
       
   }
   public List<Editorial> listarEditoriales(){
        List<Editorial> editoriales = new ArrayList();
   editoriales = editorialRepositorio.findAll();
        return editoriales;
  
        
   }
   
 public void modificarEditorial(String nombre, String id){
      Optional<Editorial> respuestaEditorial =  editorialRepositorio.findById(id);
      if (respuestaEditorial.isPresent()) {
          Editorial editorial = new Editorial();
          editorial.setNombre(nombre);
          editorialRepositorio.save(editorial);
      }
 }  
 @Transactional
    public Editorial getOne(String id){
        return editorialRepositorio.getOne(id);
    }
     
}

