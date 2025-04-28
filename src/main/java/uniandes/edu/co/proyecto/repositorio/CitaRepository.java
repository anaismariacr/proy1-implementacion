package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, String> {

    @Query(value = "SELECT * FROM CITAS", nativeQuery = true)
    Collection<Cita> darCitas();
    
    @Query(value = "SELECT * FROM CITAS WHERE ID = :id", nativeQuery = true)
    Optional<Cita> darCita(@Param("id") Integer id);

    //RFC PROBAR
    @Query("SELECT c FROM Cita c " +
       "JOIN c.numOrden os " +
       "JOIN os.servicioNombre s " +
       "WHERE s.nombre = :nombreServicio " +
       "AND c.fecha BETWEEN CURRENT_DATE AND CURRENT_DATE + 28")
List<Cita> findDisponibilidadServicioEnProximas4Semanas(@Param("nombreServicio") String nombreServicio);

//rfc probar
@Query("SELECT c FROM Cita c " +
       "JOIN c.numOrden os " +
       "WHERE c.numDocAfiliado.numDoc = :numDoc " +
       "AND c.tipoDocAfiliado = :tipoDoc " +
       "AND c.fecha BETWEEN :inicio AND :fin")
List<Cita> findServiciosUsadosPorAfiliadoEnRango(
    @Param("numDoc") String numDoc,
    @Param("tipoDoc") String tipoDoc,
    @Param("inicio") Date inicio,
    @Param("fin") Date fin);

// RFC 5 PROBAR
    @Query(value = "SELECT s.NOMBRE, c.FECHA, c.HORA, mp.NOMBRE " +
    "FROM CITAS c " +
    "JOIN ORDENESSERVICIO os ON c.ORDENSERVICIO = os.NUMERO " +
    "JOIN SERVICIOSSALUD s ON os.SERVICIO_SALUD = s.NOMBRE " +
    "JOIN MEDICOS m ON os.MEDICO_REMITENTE = m.REGISTRO_MEDICO " +
    "JOIN MEDICOSPERSONAL mp ON m.REGISTRO_MEDICO = mp.REGISTRO_MEDICO " +
    "WHERE (:nombreServicio IS NULL OR s.NOMBRE = :nombreServicio) " +
    "AND (:registroMedico IS NULL OR m.REGISTRO_MEDICO = :registroMedico) " +
    "AND (:fechaInicio IS NULL OR c.FECHA >= :fechaInicio) " +
    "AND (:fechaFin IS NULL OR c.FECHA <= :fechaFin)",
    nativeQuery = true)
    List<Object[]> findDisponibilidadAgenda(
    @Param("nombreServicio") String nombreServicio,
    @Param("registroMedico") String registroMedico,
    @Param("fechaInicio") Date fechaInicio,
    @Param("fechaFin") Date fechaFin);  

    // RF9 ayuda
    @Query(value = "SELECT COUNT(*) " +
                   "FROM CITAS c " +
                   "JOIN ORDENESSERVICIO os ON c.ORDENSERVICIO = os.NUMERO " +
                   "JOIN MEDICOS m ON os.MEDICOREMITENTE = m.REGISTROMEDICO " +
                   "WHERE c.FECHA = :fecha " +
                   "AND c.HORA = :hora " +
                   "AND m.REGISTROMEDICO = :medicoRegistro", nativeQuery = true)
    long contarDisponibilidad(
        @Param("fecha") Date fecha,
        @Param("hora") Time hora,
        @Param("medicoRegistro") String medicoRegistro
    );

    //Agendar cita
    @Modifying
    @Query(value = "INSERT INTO CITAS (ID, FECHA, HORA, ATENDIDA_EN, TIPODOCAFILIADO, NUMDOCAFILIADO, ORDENSERVICIO) " +
                   "VALUES (:id, :fecha, :hora, :ipsNit, :tipoDocAfiliado, :numDocAfiliado, :ordenServicio)", nativeQuery = true)
    void agendarCita(
        @Param("id") String id,
        @Param("fecha") Date fecha,
        @Param("hora") Time hora,
        @Param("ipsNit") String ipsNit,
        @Param("tipoDocAfiliado") String tipoDocAfiliado,
        @Param("numDocAfiliado") String numDocAfiliado,
        @Param("ordenServicio") String ordenServicio
    );


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CITAS (ID, FECHA, HORA, ATENDIDA_EN, NUMDOCAFILIADO, ORDENSERVICIO) " +
                   "VALUES (CITAS_SEQ.nextval, :fecha, :hora, :ipsNit, :numDocAfiliado, :numOrden)", nativeQuery = true)
    void insertarCita(@Param("fecha") Date fecha, 
                      @Param("hora") Time hora, 
                      @Param("ipsNit") String ipsNit,  
                      @Param("numDocAfiliado") Integer numDocAfiliado, 
                      @Param("numOrden") String numOrden);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CITAS SET FECHA = :fecha, HORA = :hora, ATENDIDA_EN = :ipsNit, ORDENSERVICIO = :numOrden " +
                   "WHERE ID = :id", nativeQuery = true)
    void actualizarCita(@Param("id") String id, 
                        @Param("fecha") Date fecha, 
                        @Param("hora") Time hora, 
                        @Param("ipsNit") String ipsNit, 
                        @Param("numOrden") String numOrden);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CITAS WHERE ID = :id", nativeQuery = true)
    void eliminarCita(@Param("id") String id);
}