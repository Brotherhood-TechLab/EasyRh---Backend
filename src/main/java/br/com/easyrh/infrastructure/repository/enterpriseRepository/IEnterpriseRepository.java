package br.com.easyrh.infrastructure.repository.enterpriseRepository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.easyrh.domain.Entities.Enterprise;

@Repository
public interface IEnterpriseRepository extends JpaRepository<Enterprise, UUID>
{
}
