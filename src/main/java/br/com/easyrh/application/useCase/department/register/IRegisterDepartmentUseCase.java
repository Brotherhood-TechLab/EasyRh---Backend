package br.com.easyrh.application.useCase.department.register;

import org.springframework.stereotype.Service;

import br.com.easyrh.shared.request.department.RequestDepartmentRegisterJson;

@Service
public interface IRegisterDepartmentUseCase {
  public void Execute(RequestDepartmentRegisterJson request);
}
