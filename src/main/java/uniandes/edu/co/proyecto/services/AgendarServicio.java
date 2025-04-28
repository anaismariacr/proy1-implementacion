package uniandes.edu.co.proyecto.services;

import java.sql.Date;
import java.util.List;

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
    public List<Object[]> consultarDispSerializable(String nombreServicio,String registroMedico,Date fechaInicio,Date fechaFin) throws Exception {
        try {
            Thread.sleep(30000); // Sleep de 30 segundos
            return citaRepo.findDisponibilidadAgenda(nombreServicio, registroMedico, fechaInicio, fechaFin);
        } catch (Exception e) {
            throw new Exception("Error al consultar la agenda: " + e.getMessage());
        }
        
    }

    @Transactional
    public Boolean consultarDispReadCommited(){
        return true;
    }
}
