package br.com.easyrh.domain.repositories.user.readOnly;

public interface IUserReadOnlyRepository 
{
    public boolean ExisteUserByEmailOrCpf(String email, String cpf);
}
