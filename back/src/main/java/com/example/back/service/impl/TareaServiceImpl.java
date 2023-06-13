package com.example.back.service.impl;

import com.example.back.entity.Tarea;
import com.example.back.repository.TareaRepository;
import com.example.back.service.TareaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    public Tarea agregarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public Tarea getTareaById(Long tareaId) {
        Tarea tarea = tareaRepository.findById(tareaId).orElse(null);
        return tarea;
    }
    

    @Override
    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Tarea editarTarea(Tarea tarea) {
        Tarea existingTarea = tareaRepository.findById(tarea.getId()).get();
        existingTarea.setDescripcion(tarea.getDescripcion());
        existingTarea.setFechaCreacion(tarea.getFechaCreacion());
        existingTarea.setVigente(tarea.isVigente());
        Tarea updatedTarea = tareaRepository.save(existingTarea);
        return updatedTarea;
    }

    @Override
    public void eliminarTarea(Long tareaId) {
        tareaRepository.deleteById(tareaId);
    }

    @Override
    public Object editarTarea(Long any, Tarea tarea) {
        Tarea existingTarea = tareaRepository.findById(tarea.getId()).get();
        existingTarea.setDescripcion(tarea.getDescripcion());
        existingTarea.setFechaCreacion(tarea.getFechaCreacion());
        existingTarea.setVigente(tarea.isVigente());
        Tarea updatedTarea = tareaRepository.save(existingTarea);
        return updatedTarea;
    }
}
