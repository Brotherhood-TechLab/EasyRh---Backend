package br.com.easyrh.application.useCase.enterprise.register;

import org.springframework.stereotype.Service;

import br.com.easyrh.shered.request.enterprise.RequestEnterpriseRegisterJson;
import br.com.easyrh.shered.response.employee.ResponseEnterpriseRegisterJson;

@Service
public interface IRegisterEnterpriseUseCase {
    public ResponseEnterpriseRegisterJson Execute(RequestEnterpriseRegisterJson request);
}
