package br.com.easyrh.domain.service.user.registerService;

import br.com.easyrh.shared.request.user.RequestUserRegisterJson;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;

public interface IRegisterUserSerivce
{
    public ResponseUserRegisterJson RegisterUser(RequestUserRegisterJson request);
}
