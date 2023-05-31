package com.ejercicio1.prueba.servicios;




import com.ejercicio1.prueba.entidades.Autor;
import com.ejercicio1.prueba.entidades.Editorial;
import com.ejercicio1.prueba.entidades.Imagen;
import com.ejercicio1.prueba.entidades.Libro;
import com.ejercicio1.prueba.excepciones.MiExcepcion;
import com.ejercicio1.prueba.repositorios.AutorRepositorio;
import com.ejercicio1.prueba.repositorios.EditorialRepositorio;
import com.ejercicio1.prueba.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
     @Autowired
   private ImagenServicio imagenServicio;
    @Transactional
    public void crearLibro(MultipartFile archivo,Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial= new Editorial();
        
        if(respuestaAutor.isPresent()){
            
            autor = respuestaAutor.get();
        }
        
        if(respuestaEditorial.isPresent()){
            
            editorial = respuestaEditorial.get();
        }
      
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        Imagen imagen = imagenServicio.guardar(archivo);
        
        libro.setImagen(imagen);
       libroRepositorio.save(libro);
    }
    
    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;
    }
    
    @Transactional
    public void modificarLibro(MultipartFile archivo,Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial= new Editorial();
        
        if(respuestaAutor.isPresent()){
            
            autor = respuestaAutor.get();
        }
        
        if(respuestaEditorial.isPresent()){
            
            editorial = respuestaEditorial.get();
        }
        
        if(respuesta.isPresent()){
            
            Libro libro = respuesta.get();
            
                     
            libro.setTitulo(titulo);
            
            libro.setEjemplares(ejemplares);
            
            libro.setAutor(autor);
            
            libro.setEditorial(editorial);
            String idImagen = null;
           if (libro.getImagen() != null) {
              idImagen = libro.getImagen().getId();
            }
            
            Imagen imagen = imagenServicio.actualizar(idImagen, archivo);
            libro.setImagen(imagen);
            libroRepositorio.save(libro);
            
        }
    }
    
    @Transactional(readOnly = true)
    public Libro getOne(Long isbn){
       return libroRepositorio.getOne(isbn);
    }
    
    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion{
       
        if(isbn == null){
            throw new MiExcepcion("el isbn no puede ser nulo"); //
        }
        if(titulo.isEmpty() || titulo == null){
            throw new MiExcepcion("el titulo no puede ser nulo o estar vacio");
        }
        if(ejemplares == null){
            throw new MiExcepcion("ejemplares no puede ser nulo");
        }
        if(idAutor.isEmpty() || idAutor == null){
            throw new MiExcepcion("el Autor no puede ser nulo o estar vacio");
        }
        
        if(idEditorial.isEmpty() || idEditorial == null){
            throw new MiExcepcion("La Editorial no puede ser nula o estar vacia");
        }
    }

}