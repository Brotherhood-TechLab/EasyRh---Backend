package br.com.easyrh.shared.request.department;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestDepartmentRegisterJson(
    @JsonAlias("name") String Name,
    @JsonAlias("description") String Description,
    @JsonAlias("enterprise_id") String EnterpriseID) {
}
