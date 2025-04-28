package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OrdenServicio;
import uniandes.edu.co.proyecto.modelo.Afiliado;
import uniandes.edu.co.proyecto.modelo.Medico;
import uniandes.edu.co.proyecto.modelo.ServicioSalud;

@Repository
public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, String>{

    @Query(value = "SELECT * FROM ORDENESSERVICIO", nativeQuery = true)
    Collection<OrdenServicio> darOrdenes();

    @Query(value = "SELECT * FROM ORDENESSERVICIO WHERE NUMERO = :numero", nativeQuery = true)
    OrdenServicio darOrden(@Param("numero") Integer numero);

    // RFC PROBAR
    @Query("SELECT os.servicioNombre.nombre, COUNT(os) as total " +
           "FROM OrdenServicio os " +
           "GROUP BY os.servicioNombre.nombre " +
           "ORDER BY total DESC")
    List<Object[]> findTop20ServiciosMasSolicitados();

    //figure out como importar el enum de estado
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ORDENESSERVICIO (NUMERO, FECHA, ESTADO, AFILIADO_RECEPTOR_NUM, MEDICO_REMITENTE, SERVICIO_SALUD) VALUES (proyecto_sequence.nextval, :fecha, :estado, :numAfiliadoReceptor, :medicoRemitente, :servicioNombre)", nativeQuery = true)
    void insertarOrden(@Param("fecha") Date fecha, @Param("estado") String estado, @Param("numAfiliadoReceptor") Integer numAfiliadoReceptor, @Param("medicoRemitente") String medicoRemitente, @Param("servicioNombre") String servicioNombre);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE ORDENESSERVICIO SET FECHA = :fecha, ESTADO = :estado, AFILIADO_RECEPTOR_NUM = :numAfiliadoReceptor, MEDICO_REMITENTE = :medicoRemitente, SERVICIO_SALUD = :servicioNombre WHERE NUMERO = :numero", nativeQuery = true)
    void actualizarOrden(@Param("numero") Integer numero, @Param("fecha") Date fecha, @Param("estado") String estado, @Param("numAfiliadoReceptor") Integer numAfiliadoReceptor, @Param("medicoRemitente") String medicoRemitente, @Param("servicioNombre") String servicioNombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ORDENESSERVICIO WHERE NUMERO = :numero", nativeQuery = true)
    void eliminarOrden(@Param("numero") Integer numero);
}
