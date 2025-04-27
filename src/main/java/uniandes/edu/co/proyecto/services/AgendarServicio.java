package uniandes.edu.co.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.modelo.ServicioSalud;
import uniandes.edu.co.proyecto.repositorio.CitaRepository;
import uniandes.edu.co.proyecto.repositorio.OrdenServicioRepository;

public class AgendarServicio {
    @Autowired
    private OrdenServicioRepository ordenRepo;

    @Autowired
    private CitaRepository citaRepo;

    @Transactional
    public String agendarCita(ServicioSalud servicio, Cita cita){

        return "No hay nada";
    }

    @Transactional
    public Boolean consultarDispSerializable(){
        return true;
    }

    @Transactional
    public Boolean consultarDispReadCommited(){
        return true;
    }
}
