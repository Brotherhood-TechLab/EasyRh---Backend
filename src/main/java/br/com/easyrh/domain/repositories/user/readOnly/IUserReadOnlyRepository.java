package br.com.easyrh.domain.repositories.user.readOnly;

import br.com.easyrh.domain.Entities.User;

public interface IUserReadOnlyRepository
{
  public boolean ExistUserByEmailOrCpf(String email, String cpf);

  public User FindByCPF(String cpf);

  public boolean EmailAlreadyRegistred (String email);
}
