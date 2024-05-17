package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.application.useCase.user.edit.IEditUserUseCase;
import br.com.easyrh.application.useCase.user.register.IRegisterUserUseCase;
import br.com.easyrh.shared.request.user.RequestUserEditJson;
import br.com.easyrh.shared.request.user.RequestUserRegisterJson;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/user/v1")
@Tag(name = "User", description = "Endpoint for managing users")
public class UserController {
  @Autowired
  private final IRegisterUserUseCase _registerUserUseCase;

  @Autowired
  private final IEditUserUseCase _editUserUseCase;

  public UserController(IRegisterUserUseCase registerUserUseCase, IEditUserUseCase editUserUseCase) {
    this._registerUserUseCase = registerUserUseCase;
    this._editUserUseCase = editUserUseCase;
  }

  @PostMapping("/register")
  @Operation(summary = "Register a new user", description = "Register a new user", tags = { "User" }, responses = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseUserRegisterJson.class))
      }),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  public ResponseEntity<ResponseUserRegisterJson> Register(@RequestBody @Validated RequestUserRegisterJson request) {
    var result = _registerUserUseCase.Execute(request);
    return ResponseEntity.ok(result);
  }

  @PutMapping("/edit")
  @Operation(summary = "Edit a user", description = "Edit a user", tags = { "User" }, responses = {
      @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  public ResponseEntity<String> Edit(@RequestBody @Validated RequestUserEditJson request) {
    _editUserUseCase.Execute(request);
    return ResponseEntity.ok("Successful operation");
  }
}
