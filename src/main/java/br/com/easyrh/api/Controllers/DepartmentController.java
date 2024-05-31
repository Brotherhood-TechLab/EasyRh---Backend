package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.application.useCase.department.register.IRegisterDepartmentUseCase;
import br.com.easyrh.shared.request.department.RequestDepartmentRegisterJson;

@RestController
@RequestMapping("api/department/v1")
public class DepartmentController {
  @Autowired
  private IRegisterDepartmentUseCase _registerDepartmentUseCase;

  public DepartmentController(IRegisterDepartmentUseCase registerDepartmentUseCase) {
    this._registerDepartmentUseCase = registerDepartmentUseCase;
  }

  @PostMapping("/register")
  public ResponseEntity<?> Register(@RequestBody @Validated RequestDepartmentRegisterJson request) {
    _registerDepartmentUseCase.Execute(request);
    return ResponseEntity.noContent().build();
  }
}
