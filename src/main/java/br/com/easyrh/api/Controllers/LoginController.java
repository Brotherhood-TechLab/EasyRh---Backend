package br.com.easyrh.api.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.shered.request.login.RequestLoginJson;

@RestController
@RequestMapping("/auth")
public class LoginController 
{

    @RequestMapping("/login")
    public ResponseEntity login(@RequestBody RequestLoginJson request)
    {
        return ResponseEntity.ok().build();
    }
}
