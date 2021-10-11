package br.com.hdi.apijava.db.repository;

import br.com.hdi.apijava.db.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByDocumento(Long documento);
    List<UsuarioEntity> findAll();
  //  List<UsuarioEntity> getGarantia(@Param("cnpj") String cnpj);

}
