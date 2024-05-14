package br.com.easyrh.shared.response.user;

import br.com.easyrh.domain.Enum.Role;

public class ResponseUserRegisterJson {
  private String Name;
  private String AccessToken;
  private String Email;
  private Role Role;

  public ResponseUserRegisterJson(String name, String accessToken, String email, Role role) {
    Name = name;
    AccessToken = accessToken;
    Email = email;
    Role = role;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getAccessToken() {
    return AccessToken;
  }

  public void setAccessToken(String accessToken) {
    AccessToken = accessToken;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String Email) {
    this.Email = Email;
  }

  public Role getRole() {
    return Role;
  }

  public void setRole(Role role) {
    Role = role;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((Name == null) ? 0 : Name.hashCode());
    result = prime * result + ((AccessToken == null) ? 0 : AccessToken.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ResponseUserRegisterJson other = (ResponseUserRegisterJson) obj;
    if (Name == null) {
      if (other.Name != null)
        return false;
    } else if (!Name.equals(other.Name))
      return false;
    if (AccessToken == null) {
      if (other.AccessToken != null)
        return false;
    } else if (!AccessToken.equals(other.AccessToken))
      return false;
    return true;
  }

}
