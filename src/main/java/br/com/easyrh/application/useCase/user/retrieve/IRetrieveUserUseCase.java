package br.com.easyrh.application.useCase.user.retrieve;

import br.com.easyrh.shared.response.user.ResponseUserRepresentation;

public interface IRetrieveUserUseCase {
  public ResponseUserRepresentation Execute(String cpf);
}
