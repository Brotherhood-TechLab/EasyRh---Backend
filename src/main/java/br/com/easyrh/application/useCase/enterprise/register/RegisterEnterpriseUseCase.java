package br.com.easyrh.application.useCase.enterprise.register;

import static br.com.easyrh.application.Utils.errorMessageOnValidation.ErrorMessage.GetErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import br.com.easyrh.domain.service.enterprise.register.IRegisterEnterpriseService;
import br.com.easyrh.exceptions.ErrorOnValidationException;
import br.com.easyrh.shared.request.enterprise.RequestEnterpriseRegisterJson;
import br.com.easyrh.shared.response.enterprise.ResponseEnterpriseRegisterJson;

@Service // Informa que a classe é um Serviço que será injetada pelo Spring
public class RegisterEnterpriseUseCase implements IRegisterEnterpriseUseCase {
  // private final AtomicLong id = new AtomicLong();
  // private Logger logger =
  // Logger.getLogger(RegisterableService.class.getName());

  @Autowired
  private RegisterEnterpriseValidator _registerEnterpriseValidator;

  @Autowired
  private IRegisterEnterpriseService _registerEnterpriseService;

  public RegisterEnterpriseUseCase(RegisterEnterpriseValidator registerEnterpriseValidator,
      IRegisterEnterpriseService registerEnterpriseService) {
    _registerEnterpriseValidator = registerEnterpriseValidator;
    _registerEnterpriseService = registerEnterpriseService;
  }

  @Override
  public ResponseEnterpriseRegisterJson Execute(RequestEnterpriseRegisterJson request) {
    ValidateRequest(request);

    return _registerEnterpriseService.RegisterEnterprise(request);
  }

  private void ValidateRequest(RequestEnterpriseRegisterJson enterprise) {
    var result = BuildValidate(enterprise);

    if (result.hasErrors()) {
      var message = GetErrorMessage(result);
      throw new ErrorOnValidationException(message);
    }
  }

  private Errors BuildValidate(RequestEnterpriseRegisterJson enterprise) {
    Errors errors = new BeanPropertyBindingResult(enterprise, "enterprise");

    _registerEnterpriseValidator.validate(enterprise, errors);

    return errors;
  }
}
