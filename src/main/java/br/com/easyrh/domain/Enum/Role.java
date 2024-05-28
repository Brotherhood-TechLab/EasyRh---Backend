package br.com.easyrh.domain.Enum;

public enum Role {
  ADMIN("admin"),
  USER("user");

  private String Role;

  Role(String role) {
    this.Role = role;
  }

  public String getRole() {
    return Role;
  }

  public boolean getBoolRole() {
    if (Role == "admin") {
      return true;
    } else {
      return false;
    }
  }
}
