package br.com.easyrh.application.useCase.enterprise.register;

import org.springframework.stereotype.Service;

import br.com.easyrh.shared.request.enterprise.RequestEnterpriseRegisterJson;
import br.com.easyrh.shared.response.enterprise.ResponseEnterpriseRegisterJson;

@Service
public interface IRegisterEnterpriseUseCase {
  public ResponseEnterpriseRegisterJson Execute(RequestEnterpriseRegisterJson request);
}
