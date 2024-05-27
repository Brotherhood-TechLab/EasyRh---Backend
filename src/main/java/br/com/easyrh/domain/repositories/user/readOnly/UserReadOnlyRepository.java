package br.com.easyrh.domain.repositories.user.readOnly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easyrh.domain.Entities.User;
import br.com.easyrh.exceptions.ErrorOnQueryException;
import br.com.easyrh.infrastructure.repository.userRepository.IUserRepository;

@Service
public class UserReadOnlyRepository implements IUserReadOnlyRepository {
  private final IUserRepository _userRepository;

  @Autowired
  public UserReadOnlyRepository(IUserRepository _userRepository) {
    this._userRepository = _userRepository;
  }

  @Override
  public boolean ExistUserByEmailOrCpf(String email, String cpf) {
    return _userRepository.findByEmailAndCPF(email, cpf).isPresent();
  }

  @Override
  public User FindByCPF(String cpf) {
    return _userRepository.findByCpf(cpf).map(User.class::cast)
        .orElseThrow(() -> new ErrorOnQueryException("Usuário não cadastrado"));
  }
}
