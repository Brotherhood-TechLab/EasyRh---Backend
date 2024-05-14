package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.application.useCase.login.IUserLoginUseCase;
import br.com.easyrh.shared.request.login.RequestLoginJson;
import br.com.easyrh.shared.response.login.ResponseLoginJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/auth/v1")
@Tag(name = "Auth", description = "Endpoint for authentication")
public class LoginController {
  private final IUserLoginUseCase _userLoginUseCase;

  @Autowired
  public LoginController(IUserLoginUseCase userLoginUseCase) {
    this._userLoginUseCase = userLoginUseCase;
  }

  @PostMapping("api/login/v1")
  @Operation(summary = "Login a user", description = "Login a user", tags = { "Auth" }, responses = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseLoginJson.class))
      }),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  public ResponseEntity Login(@RequestBody @Validated RequestLoginJson request) {
    var result = _userLoginUseCase.Execute(request);
    return ResponseEntity.ok(result);
  }
}
