package uniandes.edu.co.proyecto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.ServicioIps;
import uniandes.edu.co.proyecto.modelo.ServicioIpsPK;
import uniandes.edu.co.proyecto.modelo.ServicioSalud;
import uniandes.edu.co.proyecto.repositorio.ServicioIpsRepository;

@RestController
public class ServicioIpsController {

    @Autowired
    private ServicioIpsRepository servicioIpsRepository;

    @GetMapping("/serviciosIps")
    public String serviciosIps(Model model) {
        model.addAttribute("serviciosIps", servicioIpsRepository.darServiciosIps());
        return "serviciosIps";
    }

    @GetMapping("/serviciosIps/new")
    public String servicioIpsForm(Model model) {
        model.addAttribute("servicioIps", new ServicioIps());
        return "servicioIpsNuevo";
    }

    @PostMapping("/serviciosIps/new/save")
    public String servicioIpsGuardar(@RequestParam("NOMBRESERVICIO") String nombreServicio, @RequestParam("NITIPS") String nitIps) {
        servicioIpsRepository.insertarServicioIps(nombreServicio, nitIps);
    return "redirect:/serviciosIps";
    }




    

    @GetMapping("/serviciosIps/{NOMBRESERVICIO}/{NITIPS}/edit")
    public String servicioIpsEditarForm(@PathVariable("NOMBRESERVICIO") String nombre, @PathVariable("NITIPS") String nitips, Model model) {
        Optional<ServicioIps> servicioIps = servicioIpsRepository.darServicioIps(nombre, nitips);
        if (servicioIps.isPresent()) {
            model.addAttribute("servicioIps", servicioIps.get());
            return "servicioIpsEditar";
        } else {
            return "redirect:/serviciosIps";
        }
    }

   //No se puede actualizar porque no tienen mas datos que la PK compuesta

   @GetMapping("/serviciosIps/{NOMBRESERVICIO}/{NITIPS}/delete")
    public String servicioSaludEliminar(@PathVariable("NOMBRESERVICIO") String nombre, @PathVariable("NITIPS") String nitips) {
        servicioIpsRepository.eliminarServicioIps(nombre, nitips);;
        return "redirect:/serviciosSalud";
    }
    
}
