package br.com.easyrh.infrastructure.repository.departmentRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.easyrh.domain.Entities.Department;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, UUID> {
  // @Query("SELECT p FROM Department p WHERE p.IdNumber = :idNumber")
  // Optional<Department> findByIdNumber(String idNumber);
}
