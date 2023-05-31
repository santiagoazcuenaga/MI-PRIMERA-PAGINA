/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejercicio1.prueba.controladores;

import com.ejercicio1.prueba.entidades.Autor;
import com.ejercicio1.prueba.excepciones.MiExcepcion;
import com.ejercicio1.prueba.servicios.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Usuario
 */
@Controller
@RequestMapping("/autor") // localhost:8080/autor
public class AutorControlador {
  @Autowired
  private AutorServicio autorservicio;
    
    @GetMapping("/registrar") //localhost:8080/autor/registrar  
   public String registrar(){
      return "Autor_form.html"; 
   } 
   
   @PostMapping("/registro")
   public String registro(@RequestParam String nombre){
      try {
          autorservicio.crearAutor(nombre);
      } catch (MiExcepcion ex) {
          Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
             return "Autor_form.html";
      }
       
       return "index.html";
   }

   @GetMapping("/lista")
public String listar (ModelMap modelo){
  List <Autor> autores = autorservicio.listarAutores();
  modelo.addAttribute("autores",autores);
   return "autor_list.html"; 
}

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("autor", autorservicio.getOne(id));
        
        return "autor_modificar.html";
    }
    
 @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){
      
        try {
            autorservicio.modificarAutor(nombre, id);
            
            return "redirect:../lista";
        } catch (MiExcepcion ex) {
            modelo.put("error", ex.getMessage());
            return "autor_modificar.html";
        }
        
    }
    
}
