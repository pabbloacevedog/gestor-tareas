package com.example.back.controller;
import com.example.back.entity.Tarea;
import com.example.back.repository.TareaRepository;
import com.example.back.service.TareaService;
import com.example.back.service.impl.TareaServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TareaControllerTest {
    @Mock
    private TareaRepository tareaRepository;

    private TareaService tareaService;
    private TareaController tareaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        tareaService = new TareaServiceImpl(tareaRepository);
        tareaController = new TareaController(tareaService);
    }

    @Test
    public void listarTareas_debeRetornarListaDeTareas() {
        List<Tarea> tareas = new ArrayList<>();
        tareas.add(new Tarea("Tarea 1", new Date(), true));
        tareas.add(new Tarea("Tarea 2", new Date(), false));

        when(tareaRepository.findAll()).thenReturn(tareas);

        List<Tarea> resultado = tareaController.listarTareas();

        assertEquals(2, resultado.size());
        assertEquals("Tarea 1", resultado.get(0).getDescripcion());
        assertEquals("Tarea 2", resultado.get(1).getDescripcion());
    }

    @Test
    public void agregarTarea_debeRetornarTareaCreada() {
        Tarea tareaNueva = new Tarea("Nueva tarea", new Date(), true);
        Tarea tareaCreada = new Tarea("Nueva tarea", new Date(), true);

        when(tareaRepository.save(tareaNueva)).thenReturn(tareaCreada);

        ResponseEntity<Tarea> respuesta = tareaController.agregarTarea(tareaNueva);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        assertEquals(1L, respuesta.getBody().getId());
        assertEquals("Nueva tarea", respuesta.getBody().getDescripcion());
        assertEquals(true, respuesta.getBody().isVigente());
    }

    @Test
    public void agregarTarea_sinDescripcion_debeRetornarBadRequest() {
        Tarea tareaNueva = new Tarea(null, new Date(), true);

        ResponseEntity<Tarea> respuesta = tareaController.agregarTarea(tareaNueva);

        assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
        assertNull(respuesta.getBody());
    }

    @Test
    public void editarTarea_existente_debeRetornarTareaModificada() {
        Tarea tareaExistente = new Tarea("Tarea existente", new Date(), true);

        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tareaExistente);

        Tarea tareaModificada = new Tarea("Tarea modificada", new Date(), false);
        ResponseEntity<Tarea> respuesta = tareaController.editarTarea(1L, tareaModificada);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(1L, respuesta.getBody().getId());
        assertEquals("Tarea modificada", respuesta.getBody().getDescripcion());
        assertEquals(false, respuesta.getBody().isVigente());
    }

    @Test
    public void editarTarea_noExistente_debeRetornarNotFound() {
        when(tareaRepository.findById(1L)).thenReturn(Optional.empty());

        Tarea tareaModificada = new Tarea("Tarea modificada", new Date(), false);
        ResponseEntity<Tarea> respuesta = tareaController.editarTarea(1L, tareaModificada);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
        assertNull(respuesta.getBody());
    }

    @Test
    public void eliminarTarea_existente_debeRetornarOk() {
        when(tareaRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> respuesta = tareaController.eliminarTarea(1L);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        verify(tareaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void eliminarTarea_noExistente_debeRetornarNotFound() {
        when(tareaRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Void> respuesta = tareaController.eliminarTarea(1L);

        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
        verify(tareaRepository, times(0)).deleteById(1L);
    }
}