package br.com.easyrh.domain.service.login;

import br.com.easyrh.shared.request.login.RequestLoginJson;
import br.com.easyrh.shared.response.login.ResponseLoginJson;

public interface ILoginService
{
    public ResponseLoginJson DoLogin(RequestLoginJson request);
}
