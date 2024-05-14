package br.com.easyrh.domain.repositories.user.writeOnly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.easyrh.domain.Entities.User;
import br.com.easyrh.infrastructure.repository.userRepository.IUserRepository;

@Service
public class UserWriteOnlyRepository implements IUserWriteOnlyRepository {
  private final IUserRepository _userRepository;

  @Autowired
  public UserWriteOnlyRepository(IUserRepository _userRepository) {
    this._userRepository = _userRepository;
  }

  @Override
  public void SaveUser(User user) {
    _userRepository.save(user);
  }

}
