package br.com.easyrh.application.useCase.user.retrieve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easyrh.domain.repositories.user.readOnly.IUserReadOnlyRepository;
import br.com.easyrh.shared.response.user.ResponseUserRepresentation;

@Service
public class RetrieveUserUseCase implements IRetrieveUserUseCase {
  private final IUserReadOnlyRepository _readOnlyRepository;

  @Autowired
  public RetrieveUserUseCase(IUserReadOnlyRepository readOnlyRepository) {
    this._readOnlyRepository = readOnlyRepository;
  }

  @Override
  public ResponseUserRepresentation Execute(String cpf) {
    var user = _readOnlyRepository.FindByCPF(cpf);
    return user.toDTO();
  }
}
