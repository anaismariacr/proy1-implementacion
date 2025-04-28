package uniandes.edu.co.proyecto.services;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.modelo.ServicioSalud;
import uniandes.edu.co.proyecto.repositorio.CitaRepository;
import uniandes.edu.co.proyecto.repositorio.OrdenServicioRepository;

@Service
public class AgendarServicio {
    @Autowired
    private OrdenServicioRepository ordenRepo;

    @Autowired
    private CitaRepository citaRepo;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void agendarServicio(
        String id,
            Date fecha,
            Time hora,
            String medicoRegistro,
            String ipsNit,
            String tipoDocAfiliado,
            String numDocAfiliado,
            String ordenServicio) throws Exception {
        
            // Confirmar disponibilidad
            /* 
        long citasExistentes = citaRepo.contarDisponibilidad(fecha, hora, medicoRegistro);

            if (citasExistentes > 0) {
                throw new Exception("El servicio ya fue agendado por otro usuario. Intenta con otro horario.");
            }
*/
            // Insertar nueva cita
            citaRepo.agendarCita(id,fecha, hora, ipsNit, tipoDocAfiliado, numDocAfiliado, ordenServicio);

        
    }

    
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<Object[]> consultarDispSerializable(String nombreServicio,String registroMedico,Date fechaInicio,Date fechaFin) throws Exception {
        try {
            Thread.sleep(30000); // Sleep de 30 segundos
            return citaRepo.findDisponibilidadAgenda(nombreServicio, registroMedico, fechaInicio, fechaFin);
        } catch (Exception e) {
            throw new Exception("Error al consultar la agenda: " + e.getMessage());
        }
        
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public List<Object[]> consultarDispReadCommited(String nombreServicio,String registroMedico,Date fechaInicio,Date fechaFin) throws Exception {
        try {
            Thread.sleep(30000); // Sleep de 30 segundos
            return citaRepo.findDisponibilidadAgenda(nombreServicio, registroMedico, fechaInicio, fechaFin);
        } catch (Exception e) {
            throw new Exception("Error al consultar la agenda: " + e.getMessage());
        }
    }
}
