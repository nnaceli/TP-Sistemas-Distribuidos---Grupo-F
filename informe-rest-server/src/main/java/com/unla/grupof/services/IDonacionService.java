package com.unla.grupof.services;

import java.util.List;
import com.unla.grupof.entities.Donacion;

public interface IDonacionService {
    
    // Define el método para obtener todas las donaciones.
    public List<Donacion> getAll();
}
