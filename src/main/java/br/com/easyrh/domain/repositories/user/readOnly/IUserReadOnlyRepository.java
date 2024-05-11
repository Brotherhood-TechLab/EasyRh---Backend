package br.com.easyrh.domain.repositories.user.readOnly;

public interface IUserReadOnlyRepository 
{
    public boolean ExistsUserByEmail(String email);

    public boolean ExistsUserByCpf(String email);
}
