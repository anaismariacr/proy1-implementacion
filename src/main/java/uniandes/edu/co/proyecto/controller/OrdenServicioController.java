package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.OrdenServicio;
import uniandes.edu.co.proyecto.repositorio.OrdenServicioRepository;

@RestController
public class OrdenServicioController {
    
    @Autowired
    private OrdenServicioRepository ordenRepo;

    @GetMapping("/ordenes")
    public String ordenes(Model model){
        model.addAttribute("ordenes", ordenRepo.darOrdenes());
        return "ordenes";
        //return model.toString();
    }

    @GetMapping("/ordenes/new")
    public String ordenForm(Model model){
        model.addAttribute("orden", new OrdenServicio());
        return "ordenNuevo";
    }

    @PostMapping("/ordenes/new/save")
    public String ordenGuardar(@ModelAttribute OrdenServicio orden) {
        ordenRepo.insertarOrden(orden.getFecha(), orden.getEstado().toString(), orden.getNumAfiliadoReceptor().getNumDoc(), orden.getMedicoRemitente().getRegistroMedico(), orden.getServicioNombre().getNombre());
        return "redirect:/ordenes";
    }

    @GetMapping("/ordenes/{numero}/edit")
    public String epsEditarForm(@PathVariable("numero") Integer numero, Model model) {
        OrdenServicio orden = ordenRepo.darOrden(numero);
        if(orden != null) {
            model.addAttribute("orden", orden);
            return "ordenEditar";
        }
        else{
            return "redirect:/ordenes";
        }  
    }

    @PostMapping("/ordenes/{numero}/edit/save")
    public String epsEditarGuardar(@PathVariable("nit") Integer numero, @ModelAttribute OrdenServicio orden) {
        ordenRepo.actualizarOrden(numero, orden.getFecha(), orden.getEstado().toString(),
                                    orden.getNumDoc(orden.getNumAfiliadoReceptor()), orden.getRegistroMedico(orden.getMedicoRemitente()), orden.getNombreServicio(orden.getServicioNombre()));
        return "redirect:/ordenes";
    }

    @GetMapping("/ordenes/{numero}/delete")
    public String ordenEliminar(@PathVariable("numero") Integer numero) {
        ordenRepo.eliminarOrden(numero);
        return  "redirect:/ordenes";
    }
    
}
