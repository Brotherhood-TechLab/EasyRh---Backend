package br.com.easyrh.application.useCase.user.edit;

import br.com.easyrh.shared.request.user.RequestUserEditJson;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;

public interface IEditUserUseCase
{
  public ResponseUserRegisterJson Execute(RequestUserEditJson request);
}
