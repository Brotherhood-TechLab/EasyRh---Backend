package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.application.useCase.login.IUserLoginUseCase;
import br.com.easyrh.shered.request.login.RequestLoginJson;

@RestController
@RequestMapping("/auth")
public class LoginController 
{
    private final IUserLoginUseCase _userLoginUseCase;

    @Autowired
    public LoginController(IUserLoginUseCase userLoginUseCase)
    {
        this._userLoginUseCase = userLoginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity Login (@RequestBody @Validated RequestLoginJson request) 
    {
        var result = _userLoginUseCase.Execute(request);
        return ResponseEntity.ok(result);
    }
}
