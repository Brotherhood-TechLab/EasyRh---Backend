package br.com.easyrh.application.useCase.login;

import br.com.easyrh.shared.request.login.RequestLoginJson;
import br.com.easyrh.shared.response.login.ResponseLoginJson;

public interface IUserLoginUseCase {
  public ResponseLoginJson Execute(RequestLoginJson request);
}
