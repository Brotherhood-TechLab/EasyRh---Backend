package br.com.easyrh.domain.service.department.register;

import org.springframework.stereotype.Service;

import br.com.easyrh.domain.Entities.Department;
import br.com.easyrh.exceptions.ErrorOnQueryException;
import br.com.easyrh.infrastructure.repository.departmentRepository.IDepartmentRepository;
import br.com.easyrh.infrastructure.repository.enterpriseRepository.IEnterpriseRepository;
import br.com.easyrh.shared.request.department.RequestDepartmentRegisterJson;

@Service
public class RegisterDepartmentService implements IRegisterDepartmentService {
  private IDepartmentRepository _departmentRepository;
  private IEnterpriseRepository _enterpriseRepository;

  public RegisterDepartmentService(IDepartmentRepository departmentRepository,
      IEnterpriseRepository enterpriseRepository) {
    _departmentRepository = departmentRepository;
    _enterpriseRepository = enterpriseRepository;
  }

  @Override
  public void RegisterDepartment(RequestDepartmentRegisterJson request) {
    SaveDepartment(request);
  }

  private Department SaveDepartment(RequestDepartmentRegisterJson request) {
    var department = BuildDepartmentEntity(request);

    _departmentRepository.save(department);

    return department;
  }

  private Department BuildDepartmentEntity(RequestDepartmentRegisterJson request) {
    var dataToPersist = new Department(request);
    var enterprise = _enterpriseRepository.findByIdNumber(request.EnterpriseID());

    if (enterprise.isPresent()) {
      dataToPersist.setEnterprise(enterprise.get());
    } else {
      throw new ErrorOnQueryException("Código da empresa não encontrado");
    }

    return dataToPersist;
  }

}
