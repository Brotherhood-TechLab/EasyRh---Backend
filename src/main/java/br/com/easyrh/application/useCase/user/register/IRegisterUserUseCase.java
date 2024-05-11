package br.com.easyrh.application.useCase.user.register;

import br.com.easyrh.shered.request.user.RequestUserRegisterJson;
import br.com.easyrh.shered.response.user.ResponseUserRegisterJson;

public interface IRegisterUserUseCase 
{
    public ResponseUserRegisterJson Execute(RequestUserRegisterJson request);
}
