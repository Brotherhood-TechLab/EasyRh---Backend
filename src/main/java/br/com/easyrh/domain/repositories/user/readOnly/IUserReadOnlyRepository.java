package br.com.easyrh.domain.repositories.user.readOnly;

import br.com.easyrh.domain.Entities.User;

public interface IUserReadOnlyRepository {
  public boolean ExistsUserByEmail(String email);

  public boolean ExistsUserByCpf(String email);

  public User FindByCPF(String cpf);
}
