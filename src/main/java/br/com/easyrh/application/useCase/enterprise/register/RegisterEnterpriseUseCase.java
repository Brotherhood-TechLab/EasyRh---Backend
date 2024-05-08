package br.com.easyrh.application.useCase.enterprise.register;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.imageio.spi.RegisterableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.shered.request.enterprise.RequestEnterpriseRegisterJson;
import br.com.easyrh.shered.response.employee.ResponseEnterpriseRegisterJson;

@Service //Informa que a classe é um Serviço que será injetada pelo Spring
public class RegisterEnterpriseUseCase implements IRegisterEnterpriseUseCase 
{
    private final AtomicLong id = new AtomicLong();
    private Logger logger = Logger.getLogger(RegisterableService.class.getName());

    @Autowired
    private RegisterEnterpriseValidator _registerEnterpriseValidator;

    
    public RegisterEnterpriseUseCase(RegisterEnterpriseValidator _registerEnterpriseValidator) {
        this._registerEnterpriseValidator = _registerEnterpriseValidator;
    }

    @Override
    public ResponseEnterpriseRegisterJson Execute(RequestEnterpriseRegisterJson request) {

        ValidateRequest(request);
        
        logger.info("Find a enterprise");

        ResponseEnterpriseRegisterJson enterprise = new ResponseEnterpriseRegisterJson(null, null);

        enterprise.setName(request.getName());
        enterprise.setAccessToken(id.toString());

        return enterprise;
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

    private List<String> GetErrorMessage(Errors error)
    {
        var message = error.getAllErrors().stream()
        .map(ex -> ex.getDefaultMessage())
        .collect(Collectors.toList());

        return message;
    }

}
