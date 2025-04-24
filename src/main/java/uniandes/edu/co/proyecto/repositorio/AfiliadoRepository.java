package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Afiliado;

@Repository
public interface AfiliadoRepository extends JpaRepository<Afiliado, Integer> {

    @Query(value = "SELECT * FROM AFILIADOS", nativeQuery = true)
    Collection<Afiliado> darAfiliados();

    @Query(value = "SELECT * FROM AFILIADOS WHERE NUMERO_DOCUMENTO = :numDoc", nativeQuery = true)
    Optional<Afiliado> darAfiliado(@Param("numDoc") Integer numDoc);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO AFILIADOS (NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, FECHA_NACIMIENTO, CIUDAD, DIRECCION, TELEFONO, EPS) " + 
            "VALUES (:numDoc, :tipoDoc, :nombre, :fechaNac, :ciudad, :direccion, :telefono, :nitEps)", nativeQuery = true)
    void insertarAfiliado(@Param("numDoc") Integer numDoc,
                            @Param("tipoDoc") String tipoDoc,  
                            @Param("nombre") String nombre, 
                            @Param("fechaNac") Date fechaNac, 
                            @Param("ciudad") String ciudad, 
                            @Param("direccion") String direccion, 
                            @Param("telefono") String telefono, 
                            @Param("nitEps") String nitEps);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AFILIADOS SET TIPO_DOCUMENTO = :tipoDoc, NOMBRE = :nombre, FECHA_NACIMIENTO = :fechaNac, CIUDAD = :ciudad, " +
                    "DIRECCION = :direccion, TELEFONO = :telefono, EPS = :nitEps " +
                    "WHERE NUMERO_DOCUMENTO = :numDoc", nativeQuery = true)
    void actualizarAfiliado(@Param("tipoDoc") String tipoDoc, 
                            @Param("numDoc") Integer numDoc, 
                            @Param("nombre") String nombre, 
                            @Param("fechaNac") Date fechaNac, 
                            @Param("ciudad") String ciudad, 
                            @Param("direccion") String direccion, 
                            @Param("telefono") String telefono, 
                            @Param("nitEps") String nitEps);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AFILIADOS WHERE NUMERO_DOCUMENTO = :numDoc", nativeQuery = true)
    void eliminarAfiliado(@Param("numDoc") Integer numDoc);
}
