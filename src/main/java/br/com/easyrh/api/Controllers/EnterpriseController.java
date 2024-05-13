package br.com.easyrh.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.application.useCase.enterprise.register.IRegisterEnterpriseUseCase;
import br.com.easyrh.shered.request.enterprise.RequestEnterpriseRegisterJson;
import br.com.easyrh.shered.response.enterprise.ResponseEnterpriseRegisterJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/enterprise/v1")
@Tag(name = "Enterprise", description = "Endpoints for Enterprise management")
public class EnterpriseController 
{
    @Autowired
    private final IRegisterEnterpriseUseCase _registerEnterpriseUseCase;

    public EnterpriseController(IRegisterEnterpriseUseCase registerEnterpriseUseCase) {
        this._registerEnterpriseUseCase = registerEnterpriseUseCase;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new enterprise",
        description = "Register a new enterprise",
        tags = {"Enterprise"},
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                             content = {
                                 @Content(
                                    mediaType = "application/json", 
                                    schema = @Schema(implementation = ResponseEnterpriseRegisterJson.class)
                                    )
                             }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<ResponseEnterpriseRegisterJson> Register(@RequestBody @Validated RequestEnterpriseRegisterJson request)
    { 
        var result = _registerEnterpriseUseCase.Execute(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getData")
    public ResponseEntity GetEnterpriseInformation()
    {
        return ResponseEntity.ok().build();
    }
}
