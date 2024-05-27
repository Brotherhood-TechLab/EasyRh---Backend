package br.com.easyrh.application.useCase.user.edit;

import br.com.easyrh.shared.request.user.RequestUserEditJson;

public interface IEditUserUseCase {
  public void Execute(RequestUserEditJson request);
}
