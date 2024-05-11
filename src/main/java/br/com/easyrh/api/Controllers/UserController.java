package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.application.useCase.user.register.IRegisterUserUseCase;
import br.com.easyrh.shered.request.user.RequestUserRegisterJson;

@RestController
@RequestMapping("/user")
public class UserController 
{
    @Autowired
    private final IRegisterUserUseCase _registerUserUseCase;

    public UserController(IRegisterUserUseCase registerUserUseCase)
    {
        this._registerUserUseCase = registerUserUseCase;
    }

    @RequestMapping("/register")
    public ResponseEntity Register(@RequestBody @Validated RequestUserRegisterJson request)
    {
        var result = _registerUserUseCase.Execute(request);
        return ResponseEntity.ok(result);
    }
    // criar endpoint para mudar a informações do usuario
}
