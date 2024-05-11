package br.com.easyrh.domain.repositories.user.writeOnly;

import br.com.easyrh.domain.Entities.User;

public interface IUserWriteOnlyrepository 
{
    public void SaveUser(User user);
}
