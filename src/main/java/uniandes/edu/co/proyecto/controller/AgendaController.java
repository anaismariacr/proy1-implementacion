package uniandes.edu.co.proyecto.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.services.AgendarServicio;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired 
    AgendarServicio agendarServicio;

    @GetMapping("/disponibilidad")
    public List<Object[]> consultarDisponibilidad(
            @RequestParam(required = false) String nombreServicio,
            @RequestParam(required = false) String registroMedico,
            @RequestParam(required = false) Date fechaInicio,
            @RequestParam(required = false) Date fechaFin) throws Exception {
        return agendarServicio.consultarDispSerializable(nombreServicio, registroMedico, fechaInicio, fechaFin);
    }

}
