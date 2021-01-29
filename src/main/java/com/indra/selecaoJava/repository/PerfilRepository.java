package com.indra.selecaoJava.repository;

import com.indra.selecaoJava.model.entidade.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query(
            value = "SELECT per.* FROM usr_perfil usr, perfil per, usuario u, pessoa p "
            +"WHERE p.id = u.id_pessoa "
            +"AND u.id = usr.id_usuario "
            +"AND usr.id_perfil = per.id "
            +"AND u.id = ?1",
            nativeQuery = true)
    public List<Perfil> buscarPerfisUsuario(Long idUser);

}

