package br.com.easyrh.domain.service.login;

import br.com.easyrh.infrastructure.security.jwt.IGenereteToken;
import br.com.easyrh.shared.request.login.RequestLoginJson;
import br.com.easyrh.shared.response.login.ResponseLoginJson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService
{
    private final AuthenticationManager _authenticationManager;
    private final IGenereteToken _genereteToken;

    public LoginService(AuthenticationManager authenticationManager,
                        IGenereteToken genereteToken) {
        _authenticationManager = authenticationManager;
        _genereteToken = genereteToken;
    }

    @Override
    public ResponseLoginJson DoLogin(RequestLoginJson request)
    {
        UserValidate(request);

        return BuildResponse(request);
    }

    private void UserValidate(RequestLoginJson request) {
        _authenticationManager.authenticate(GetUsernamePasswordToken(request));
    }

    private UsernamePasswordAuthenticationToken GetUsernamePasswordToken(RequestLoginJson request) {
        return new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
    }

    private ResponseLoginJson BuildResponse(RequestLoginJson request) {
        return new ResponseLoginJson(
                GetToken(request.getEmail()),
                request.getEmail());
    }

    private String GetToken(String email) {
        return _genereteToken.GenereteToken(email);
    }
}
