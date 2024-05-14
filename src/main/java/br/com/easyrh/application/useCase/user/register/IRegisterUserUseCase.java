package br.com.easyrh.application.useCase.user.register;

import br.com.easyrh.shared.request.user.RequestUserRegisterJson;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;

public interface IRegisterUserUseCase {
  public ResponseUserRegisterJson Execute(RequestUserRegisterJson request);
}
