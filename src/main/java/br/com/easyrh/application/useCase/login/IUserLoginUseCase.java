package br.com.easyrh.application.useCase.login;

import br.com.easyrh.shered.request.login.RequestLoginJson;
import br.com.easyrh.shered.response.login.ResponseLoginJson;

public interface IUserLoginUseCase 
{
    public ResponseLoginJson Execute(RequestLoginJson request);
}
