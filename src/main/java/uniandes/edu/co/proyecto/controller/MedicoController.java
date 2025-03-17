package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Medico;
import uniandes.edu.co.proyecto.repositorio.MedicoRepository;

@Controller
public class MedicoController {
    
    @Autowired
    private MedicoRepository medicoRepo;

    @GetMapping("/medicos")
    public String medicos(Model model){
        model.addAttribute("medicos", medicoRepo.darMedicos());
        return "medicos";
        //return model.toString();
    }

    @GetMapping("/medicos/new")
    public String medicoForm(Model model){
        model.addAttribute("medico", new Medico());
        return "medicoNuevo";
    }

    @PostMapping("/medicos/new/save")
    public String medicoGuardar(@ModelAttribute Medico medico) {
        medicoRepo.insertarMedico(medico.getRegistroMedico(), medico.getEspecialidad(), medico.getNitIps());
        return "redirect:/medicos";
        
    }

    @GetMapping("/medicos/{registroMedico}/edit")
    public String medicoEditarForm(@PathVariable("registroMedico") String registroMedico, Model model) {
        Medico medico = medicoRepo.darMedico(registroMedico);
        if(medico != null) {
            model.addAttribute("medico", medico);
            return "medicoEditar";
        }
        else{
            return "redirect:/medicos";
        }  
    }

    @PostMapping("/medicos/{registroMedico}/edit/save")
    public String medicoEditarGuardar(@PathVariable("registroMedico") String registroMedico, @ModelAttribute Medico medico) {
        medicoRepo.actualizarMedico(registroMedico, medico.getEspecialidad(), medico.getNitIps());
        
        return "redirect:/medicos";
    }

    @GetMapping("/medicos/{registroMedico}/delete")
    public String medicoEliminar(@PathVariable("registroMedico") String registroMedico) {
        medicoRepo.eliminarMedico(registroMedico);
        return  "redirect:/medicos";
    }    
}
