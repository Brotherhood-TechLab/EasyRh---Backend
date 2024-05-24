package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.application.useCase.user.edit.IEditUserUseCase;
import br.com.easyrh.application.useCase.user.register.IRegisterUserUseCase;
import br.com.easyrh.application.useCase.user.retrieve.IRetrieveUserUseCase;
import br.com.easyrh.shared.request.user.RequestUserEditJson;
import br.com.easyrh.shared.request.user.RequestUserRegisterJson;
import br.com.easyrh.shared.response.user.ResponseUserRegisterJson;
import br.com.easyrh.shared.response.user.ResponseUserRepresentation;
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

  @Autowired
  private final IRetrieveUserUseCase _retrieveUserUseCase;

  public UserController(IRegisterUserUseCase registerUserUseCase, IEditUserUseCase editUserUseCase,
      IRetrieveUserUseCase retrieveUserUseCase) {
    this._registerUserUseCase = registerUserUseCase;
    this._editUserUseCase = editUserUseCase;
    this._retrieveUserUseCase = retrieveUserUseCase;
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

  @GetMapping("/get")
  @Operation(summary = "Retrieve a user", description = "Retrieve user information", tags = { "User" }, responses = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseUserRepresentation.class))
      }),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })

  public ResponseEntity<ResponseUserRepresentation> Retrieve(@RequestParam("cpf") String cpf) {
    var result = _retrieveUserUseCase.Execute(cpf);
    return ResponseEntity.ok(result);
  }
}
