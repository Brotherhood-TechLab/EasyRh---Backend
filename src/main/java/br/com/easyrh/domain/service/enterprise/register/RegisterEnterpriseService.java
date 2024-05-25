package br.com.easyrh.domain.service.enterprise.register;

import br.com.easyrh.domain.Entities.Enterprise;
import br.com.easyrh.infrastructure.repository.enterpriseRepository.IEnterpriseRepository;
import br.com.easyrh.shared.request.enterprise.RequestEnterpriseRegisterJson;
import br.com.easyrh.shared.response.enterprise.ResponseEnterpriseRegisterJson;
import org.springframework.stereotype.Service;

@Service
public class RegisterEnterpriseService implements IRegisterEnterpriseService
{
    private IEnterpriseRepository _repository;

    public RegisterEnterpriseService(IEnterpriseRepository repository)
    {
        _repository = repository;
    }

    @Override
    public ResponseEnterpriseRegisterJson RegisterEnterprise(RequestEnterpriseRegisterJson request)
    {
        var enterprise = SaveEnterprise(request);

        return BuildResponse(enterprise);
    }

    private Enterprise SaveEnterprise(RequestEnterpriseRegisterJson request) {
        var enterprise = BuildEnterpriseEntity(request);

        _repository.save(enterprise);

        return enterprise;
    }

    private Enterprise BuildEnterpriseEntity(RequestEnterpriseRegisterJson request) {
        var dataToPersiste = new Enterprise(request);

        return dataToPersiste;
    }

    private ResponseEnterpriseRegisterJson BuildResponse(Enterprise response) {
        return new ResponseEnterpriseRegisterJson(
                response.getName(),
                response.getGuid_Identifier());
    }
}
