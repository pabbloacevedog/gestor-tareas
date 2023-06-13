package com.example.back.controller;

import com.example.back.entity.Tarea;
import com.example.back.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaController {
    private final TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaService.listarTareas();
    }

    @PostMapping
    public ResponseEntity<Tarea> agregarTarea(@RequestBody Tarea tarea) {
        if (tarea.getDescripcion() == null || tarea.getFechaCreacion() == null) {
            return ResponseEntity.badRequest().build();
        }

        Tarea tareaGuardada = tareaService.agregarTarea(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> editarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        if (tarea.getDescripcion() == null || tarea.getFechaCreacion() == null) {
            return ResponseEntity.badRequest().build();
        }

        Tarea tareaExistente = tareaService.getTareaById(id);
        System.out.println("optionalTarea: " + tareaExistente);
        if (tareaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        tareaExistente.setDescripcion(tarea.getDescripcion());
        tareaExistente.setFechaCreacion(tarea.getFechaCreacion());
        tareaExistente.setVigente(tarea.isVigente());

        Tarea tareaActualizada = tareaService.agregarTarea(tareaExistente);
        return ResponseEntity.ok(tareaActualizada);
    }
}
