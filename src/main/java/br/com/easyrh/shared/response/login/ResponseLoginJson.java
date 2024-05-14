package br.com.easyrh.shared.response.login;

public class ResponseLoginJson {
  private String Name;
  private String AccessToken;

  public ResponseLoginJson(String AccessToken, String Name) {
    this.AccessToken = AccessToken;
    this.Name = Name;
  }

  public String getName() {
    return Name;
  }

  public void setName(String Name) {
    this.Name = Name;
  }

  public String getAccessToken() {
    return AccessToken;
  }

  public void setAccessToken(String AccessToken) {
    this.AccessToken = AccessToken;
  }
}
