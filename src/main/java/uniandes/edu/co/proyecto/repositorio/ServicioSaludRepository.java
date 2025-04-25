package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ServicioSalud;

public interface ServicioSaludRepository extends JpaRepository<ServicioSalud, String>{

    @Query(value = "SELECT * FROM SERVICIOSSALUD", nativeQuery = true)
    Collection<ServicioSalud> darServiciosSalud();

    @Query(value = "SELECT * FROM SERVICIOSSALUD WHERE NOMBRE = :nombre", nativeQuery = true)
    Optional<ServicioSalud> darServicioSalud(@Param("nombre") String nombre);

    //RFC PROBAR
    @Query("SELECT s.nombre, COUNT(c) " +
           "FROM ServicioSalud s LEFT JOIN Cita c ON s.nombre = c.numOrden.servicioNombre.nombre " +
           "GROUP BY s.nombre")
    List<Object[]> findIndiceUsoServicios();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SERVICIOSSALUD (NOMBRE, DESCRIPCION, TIPO, IPS_OFRECIDA) VALUES (:nombre, :descripcion, :tipoServicio, :ipsOfrecida)", nativeQuery = true)
    void insertarServicioSalud(@Param("nombre") String nombre, @Param("descripcion") String descripcion, 
                               @Param("tipoServicio") String tipoServicio, @Param("ipsOfrecida") String ipsOfrecida);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SERVICIOSSALUD SET DESCRIPCION = :descripcion, TIPO = :tipoServicio, IPS_OFRECIDA = :ipsOfrecida WHERE NOMBRE = :nombre", nativeQuery = true)
    void actualizarServicioSalud(@Param("nombre") String nombre, @Param("descripcion") String descripcion, 
                                @Param("tipoServicio") String tipoServicio, @Param("ipsOfrecida") String ipsOfrecida);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SERVICIOSSALUD WHERE NOMBRE = :nombre", nativeQuery = true)
    void eliminarServicioSalud(@Param("nombre") String nombre);
}
