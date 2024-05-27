package br.com.easyrh.shared.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.easyrh.shared.request.address.RequestAddressRegisterJson;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseUserRepresentation(@JsonProperty("name") String Name, @JsonProperty("email") String Email,
    @JsonProperty("password") String Password, @JsonProperty("cpf") String Cpf,
    @JsonProperty("dateofbirth") String Dateofbirth,
    @JsonProperty("gender") String Gender, @JsonProperty("phone") String Phone,
    @JsonProperty("enterpriseNumber") String EnterpriseNumber, @JsonProperty("role") boolean Role,
    @JsonProperty("address") RequestAddressRegisterJson Address) {
}
