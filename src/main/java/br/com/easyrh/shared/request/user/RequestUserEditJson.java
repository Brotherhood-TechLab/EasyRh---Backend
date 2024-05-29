package br.com.easyrh.shared.request.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.easyrh.shared.request.address.RequestAddressRegisterJson;

@JsonIgnoreProperties(ignoreUnknown = true)

public record RequestUserEditJson(@JsonAlias("name") String Name,
                                  @JsonAlias("email") String Email,
                                  @JsonAlias("cpf") String Cpf,
                                  @JsonAlias("dateofbirth") String Dateofbirth,
                                  @JsonAlias("gender") String Gender,
                                  @JsonAlias("phone") String Phone,
                                  @JsonAlias("role") boolean Role,
                                  @JsonAlias("address") RequestAddressRegisterJson Address) { }
