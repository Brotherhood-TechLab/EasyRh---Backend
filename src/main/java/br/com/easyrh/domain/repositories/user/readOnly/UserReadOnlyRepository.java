package br.com.easyrh.domain.repositories.user.readOnly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easyrh.infrastructure.repository.userRepository.IUserRepository;

@Service
public class UserReadOnlyRepository implements IUserReadOnlyRepository {
  private final IUserRepository _userRepository;

  @Autowired
  public UserReadOnlyRepository(IUserRepository _userRepository) {
    this._userRepository = _userRepository;
  }

  @Override
  public boolean ExistsUserByEmail(String email) {
    var user = _userRepository.existsByEmail(email);
    return !user.isEmpty();
  }

  @Override
  public boolean ExistsUserByCpf(String email) {
    var user = _userRepository.findByCPF(email);
    return !user.isEmpty();
  }

}
