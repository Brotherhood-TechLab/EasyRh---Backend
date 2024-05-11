package br.com.easyrh.application.useCase.enterprise.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static br.com.easyrh.application.Utils.erroMessageOnValidation.ErrorMessage.GetErrorMessage;
import br.com.easyrh.domain.Entities.Enterprise;
import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.infrastructure.repository.enterpriseRepository.IEnterpriseRepository;
import br.com.easyrh.shered.request.enterprise.RequestEnterpriseRegisterJson;
import br.com.easyrh.shered.response.enterprise.ResponseEnterpriseRegisterJson;


@Service //Informa que a classe é um Serviço que será injetada pelo Spring
public class RegisterEnterpriseUseCase implements IRegisterEnterpriseUseCase 
{
    // private final AtomicLong id = new AtomicLong();
    // private Logger logger = Logger.getLogger(RegisterableService.class.getName());

    @Autowired
    private RegisterEnterpriseValidator _registerEnterpriseValidator;
    private IEnterpriseRepository _repository;
    
    public RegisterEnterpriseUseCase(RegisterEnterpriseValidator _registerEnterpriseValidator
    ,IEnterpriseRepository repository) {
        this._registerEnterpriseValidator = _registerEnterpriseValidator;
        this._repository = repository;
    }

    @Override
    public ResponseEnterpriseRegisterJson Execute(RequestEnterpriseRegisterJson request) {

        ValidateRequest(request);

        var enterprise = SaveEnterprise(request);

        return BuildResponse(enterprise);
    }

    private void ValidateRequest(RequestEnterpriseRegisterJson enterprise) 
    {
        var result = BuildValidate(enterprise);

        if (result.hasErrors())
        {
            var message = GetErrorMessage(result);
            throw new ErrorOnValidationException(message);
        }
    } 

    private Errors BuildValidate(RequestEnterpriseRegisterJson enterprise)
    {
        Errors errors = new BeanPropertyBindingResult(enterprise, "enterprise");

        _registerEnterpriseValidator.validate(enterprise, errors);

        return errors;
    }

    private Enterprise SaveEnterprise(RequestEnterpriseRegisterJson request)
    {
        var enterprise = BuildEnterpriseEntity(request);

        _repository.save(enterprise);

        return enterprise;
    }

    private Enterprise BuildEnterpriseEntity(RequestEnterpriseRegisterJson request)
    {
        var dataToPersiste = new Enterprise(request);

        return dataToPersiste;
    }

    private ResponseEnterpriseRegisterJson BuildResponse(Enterprise response)
    {
        return new ResponseEnterpriseRegisterJson(
            response.getName(),
            response.getGuid_Identifier()
        );
    }

}
