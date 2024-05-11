package br.com.easyrh.infrastructure.repository.userRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.easyrh.domain.Entities.User;


@Repository
public interface IUserRepository extends JpaRepository<User, UUID>
{
    @Query("SELECT p FROM User p WHERE p.Email = :email")
    UserDetails findByEmail(@Param("email") String email);

    @Query("SELECT p FROM User p WHERE p.Email = :email")
    Optional<User> existsByEmail(@Param("email") String email);

    @Query("SELECT p FROM User p WHERE p.Cpf = :cpf")
    Optional<User> findBycPF(@Param("cpf") String cpf);
}
