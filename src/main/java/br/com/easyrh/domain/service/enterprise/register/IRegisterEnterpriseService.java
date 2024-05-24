package br.com.easyrh.domain.service.enterprise.register;

import br.com.easyrh.shared.request.enterprise.RequestEnterpriseRegisterJson;
import br.com.easyrh.shared.response.enterprise.ResponseEnterpriseRegisterJson;

public interface IRegisterEnterpriseService
{
    public ResponseEnterpriseRegisterJson RegisterEnterprise(RequestEnterpriseRegisterJson request);
}
