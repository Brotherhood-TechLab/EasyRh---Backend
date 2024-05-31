package br.com.easyrh.domain.service.user.edit;

import br.com.easyrh.shared.request.user.RequestUserEditJson;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;

public interface IEditUserService {
  public ResponseUserRegisterJson EditUser(RequestUserEditJson json);
}
