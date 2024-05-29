package br.com.easyrh.shared.request.department;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.easyrh.domain.Enum.Level;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestDepartmentRegisterJson(
    @JsonAlias("name") String Name,
    @JsonAlias("description") String Description,
    @JsonAlias("level") Level Level,
    @JsonAlias("enterprise_id") String EnterpriseID) {
}
