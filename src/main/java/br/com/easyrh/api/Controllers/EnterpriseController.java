package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.application.useCase.enterprise.register.IRegisterEnterpriseUseCase;
import br.com.easyrh.shered.request.enterprise.RequestEnterpriseRegisterJson;
import br.com.easyrh.shered.response.enterprise.ResponseEnterpriseRegisterJson;


@RestController
@RequestMapping("/enterprise")
public class EnterpriseController 
{
    @Autowired
    private final IRegisterEnterpriseUseCase _registerEnterpriseUseCase;

    public EnterpriseController(IRegisterEnterpriseUseCase registerEnterpriseUseCase) {
        this._registerEnterpriseUseCase = registerEnterpriseUseCase;
    }

    @RequestMapping(value = "/register"
    , method = RequestMethod.POST 
    , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEnterpriseRegisterJson> Register(@RequestBody @Validated RequestEnterpriseRegisterJson request)
    { 
        var result = _registerEnterpriseUseCase.Execute(request);
        return ResponseEntity.ok(result);
    }

    @RequestMapping("/getData")
    public ResponseEntity GetEnterpriseInformation()
    {
        return ResponseEntity.ok().build();
    }
}
