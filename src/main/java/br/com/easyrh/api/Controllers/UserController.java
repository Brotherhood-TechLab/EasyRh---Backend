package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.application.useCase.user.register.IRegisterUserUseCase;
import br.com.easyrh.shered.request.user.RequestUserRegisterJson;
import br.com.easyrh.shered.response.user.ResponseUserRegisterJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/user/v1")
@Tag(name = "User", description = "Endpoint for managing users")
public class UserController 
{
    @Autowired
    private final IRegisterUserUseCase _registerUserUseCase;

    public UserController(IRegisterUserUseCase registerUserUseCase)
    {
        this._registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user",
        description = "Register a new user",
        tags = {"User"},
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                             content = {
                                 @Content(
                                    mediaType = "application/json", 
                                    schema = @Schema(implementation = ResponseUserRegisterJson.class)
                                    )
                             }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity Register(@RequestBody @Validated RequestUserRegisterJson request)
    {
        var result = _registerUserUseCase.Execute(request);
        return ResponseEntity.ok(result);
    }

    // criar endpoint para mudar a informações do usuario
}
