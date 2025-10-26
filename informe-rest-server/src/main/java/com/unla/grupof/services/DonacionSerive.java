package com.unla.grupof.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupof.entities.Donacion;
import com.unla.grupof.repositories.IDonacionRepository;

@Service("donacionSerive")
public class DonacionSerive implements IDonacionService{
    

    @Autowired(required = true)
    private IDonacionRepository donacionRepository;
    
    @Override
    public List<Donacion> getAll(){
        List<Donacion> donaciones = donacionRepository.getAll();
        
        return donaciones;
    }

}
