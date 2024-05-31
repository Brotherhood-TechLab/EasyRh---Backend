package br.com.easyrh.domain.service.department.register;

import br.com.easyrh.shared.request.department.RequestDepartmentRegisterJson;

public interface IRegisterDepartmentService {
  public void RegisterDepartment(RequestDepartmentRegisterJson request);
}
