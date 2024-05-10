package br.com.easyrh.infrastructure.repository.personRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.easyrh.domain.Entities.Person;


@Repository
public interface IPersonRepository extends JpaRepository<Person, Long>
{
    @Query("SELECT p FROM Person p WHERE p.Email = :email")
    UserDetails findByEmail(@Param("email") String email);
}
