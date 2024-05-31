package br.com.easyrh.infrastructure.repository.enterpriseRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.easyrh.domain.Entities.Enterprise;

@Repository
public interface IEnterpriseRepository extends JpaRepository<Enterprise, UUID> {
  @Query("SELECT p FROM Enterprise p WHERE p.IdNumber = :idNumber")
  Optional<Enterprise> findByIdNumber(String idNumber);
}
