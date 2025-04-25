package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Ips;
import uniandes.edu.co.proyecto.modelo.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {
    
    @Query(value = "SELECT * FROM MEDICOS", nativeQuery = true)
    Collection<Medico> darMedicos();

    @Query(value = "SELECT * FROM MEDICOS WHERE REGISTRO_MEDICO = :registroMedico", nativeQuery = true)
    Medico darMedico(@Param("registroMedico") String registroMedico);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO MEDICOS (REGISTRO_MEDICO, ESPECIALIDAD, EMPLEADO_POR) VALUES(:registroMedico, :especialidad, :nitIps)", nativeQuery = true)
    void insertarMedico(@Param("registroMedico") String registroMedico, @Param("especialidad") String especialidad, @Param("nitIps") String nitIps);

    @Modifying
    @Transactional
    @Query(value = "UPDATE MEDICOS SET ESPECIALIDAD = :especialidad, EMPLEADO_POR = :nitIps WHERE REGISTRO_MEDICO = :registroMedico", nativeQuery = true)
    void actualizarMedico(@Param("registroMedico") String registroMedico, @Param("especialidad") String especialidad, @Param("nitIps") String nitIps);
   
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM MEDICOS WHERE REGISTRO_MEDICO = :registroMedico", nativeQuery = true)
    void eliminarMedico(@Param("registroMedico") String registroMedico);
}
