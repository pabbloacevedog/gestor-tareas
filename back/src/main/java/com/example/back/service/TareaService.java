package com.example.back.service;

import com.example.back.entity.Tarea;

import java.util.List;

public interface TareaService {
    Tarea agregarTarea(Tarea tarea);

    Tarea getTareaById(Long tareaId);

    List<Tarea> listarTareas();

    Tarea editarTarea(Tarea tarea);

    void eliminarTarea(Long tareaId);

    Object editarTarea(Long any, Tarea any2);
}
