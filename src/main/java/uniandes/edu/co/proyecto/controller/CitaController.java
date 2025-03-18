package uniandes.edu.co.proyecto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.repositorio.CitaRepository;

@RestController
public class CitaController {
    
    @Autowired
    private CitaRepository citaRepository;

    @GetMapping("/citas")
    public String citas(Model model) {
        model.addAttribute("citas", citaRepository.darCitas());
        return "citas";
    }

    @GetMapping("/citas/new")
    public String citaForm(Model model) {
        model.addAttribute("cita", new Cita());
        return "citaNuevo";
    }
    
    @PostMapping("/citas/new/save")
    public String citaGuardar(@ModelAttribute Cita cita) {
    java.sql.Date sqlDate = new java.sql.Date(cita.getFecha().getTime());

    citaRepository.insertarCita(
        sqlDate, 
        cita.getHora(), 
        cita.getIpsNit().getNit(), 
        cita.getNumDocAfiliado().getNumDoc(),
        cita.getNumOrden().getNumero()
    );
    
    return "redirect:/citas";
}

    @GetMapping("/citas/{ID}/edit")
    public String citaEditarForm(@PathVariable("ID") int id, Model model) {
        Optional<Cita> cita = citaRepository.darCita(id);
        if (cita.isPresent()) {
            model.addAttribute("cita", cita.get());
            return "citaEditar";
        } else {
            return "redirect:/citas";
        }
    }

    @PostMapping("/citas/{ID}/edit/save")
    public String citaEditarGuardar(@PathVariable("ID") int id, @ModelAttribute Cita cita) {
        java.sql.Date sqlDate = new java.sql.Date(cita.getFecha().getTime());

        citaRepository.actualizarCita(id, sqlDate, cita.getHora(), cita.getIpsNit().getNit(), cita.getNumOrden().getNumero());
        
        return "redirect:/citas"; 
    }

    @GetMapping("/citas/{id}/delete")
    public String citaEliminar(@PathVariable("id") int id) {
        citaRepository.eliminarCita(id);
        return "redirect:/citas";
    }
    
}

