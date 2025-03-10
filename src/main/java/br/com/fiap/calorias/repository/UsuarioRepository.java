package br.com.fiap.calorias.repository;

import br.com.fiap.calorias.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario , Long> {

    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
    Optional<Usuario> buscarUsuarioPeloNome(@Param("nome") String nome);

    UserDetails findByEmail(String email);

}
