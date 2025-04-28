package uniandes.edu.co.proyecto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.ServicioSalud;
import uniandes.edu.co.proyecto.repositorio.ServicioSaludRepository;

@RestController
public class ServicioSaludController {

    @Autowired
    private ServicioSaludRepository servicioSaludRepository;

    @GetMapping("/serviciosSalud")
    public String serviciosSalud(Model model) {
        model.addAttribute("serviciosSalud", servicioSaludRepository.darServiciosSalud());
        return "serviciosSalud";
    }

    @GetMapping("/serviciosSalud/new")
    public String servicioSaludForm(Model model) {
        model.addAttribute("servicioSalud", new ServicioSalud());
        return "servicioSaludNuevo";
    }
    
    @PostMapping("/serviciosSalud/new/save")
    public String servicioSaludGuardar(@ModelAttribute ServicioSalud servicioSalud) {
        servicioSaludRepository.insertarServicioSalud(servicioSalud.getNombre(), servicioSalud.getDescripcion(), 
                                servicioSalud.getTipoServicio().toString(), servicioSalud.getIpsOfrecida().getNit());
        
        return "redirect:/serviciosSalud";
    }

    @GetMapping("/serviciosSalud/{NOMBRE}/edit")
    public String servicioSaludEditarForm(@PathVariable("NOMBRE") String nombre, Model model) {
        Optional<ServicioSalud> servicioSalud = servicioSaludRepository.darServicioSalud(nombre);
        if (servicioSalud.isPresent()) {
            model.addAttribute("servicioSalud", servicioSalud.get());
            return "servicioSaludEditar";
        } else {
            return "redirect:/serviciosSalud";
        }
    }

    @PostMapping("/serviciosSalud/{NOMBRE}/edit/save")
    public String servicioSaludEditarGuardar(@PathVariable("NOMBRE") String nombre, @ModelAttribute ServicioSalud servicioSalud) {
       servicioSaludRepository.actualizarServicioSalud(nombre, servicioSalud.getDescripcion(), 
                                servicioSalud.getTipoServicio().toString(), servicioSalud.getIpsOfrecida().getNit());
        
        return "redirect:/serviciosSalud"; 
    }

    @GetMapping("/serviciosSalud/{NOMBRE}/delete")
    public String servicioSaludEliminar(@PathVariable("NOMBRE") String nombre) {
        servicioSaludRepository.eliminarServicioSalud(nombre);;
        return "redirect:/serviciosSalud";
    }

    @GetMapping("/serviciosSalud/indiceUso")
    public String indiceUso() {
        servicioSaludRepository.findIndiceUsoServicios();;
        return "redirect:/serviciosSalud";
    }
    
}
