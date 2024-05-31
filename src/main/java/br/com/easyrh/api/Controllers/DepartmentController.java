package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.domain.Entities.Department;
import br.com.easyrh.infrastructure.repository.departmentRepository.IDepartmentRepository;
import br.com.easyrh.infrastructure.repository.enterpriseRepository.IEnterpriseRepository;
import br.com.easyrh.shared.request.department.RequestDepartmentRegisterJson;

@RestController
@RequestMapping("api/department/v1")
public class DepartmentController {
  @Autowired
  private IEnterpriseRepository enterpriseRepository;
  @Autowired
  private IDepartmentRepository departmentRepository;

  @PostMapping("/register")
  public ResponseEntity<?> Register(@RequestBody RequestDepartmentRegisterJson request) {
    var enterprise = enterpriseRepository.findByIdNumber(request.EnterpriseID());
    var department = new Department(request.Name(), request.Description(), enterprise.get());
    departmentRepository.save(department);

    return ResponseEntity.noContent().build();
  }
}
