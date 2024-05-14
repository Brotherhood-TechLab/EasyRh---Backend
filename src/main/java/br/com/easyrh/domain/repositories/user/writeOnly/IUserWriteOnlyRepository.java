package br.com.easyrh.domain.repositories.user.writeOnly;

import br.com.easyrh.domain.Entities.User;

public interface IUserWriteOnlyRepository {
  public void SaveUser(User user);
}
