package br.com.easyrh.shered.response.user;

public class ResponseUserRegisterJson 
{
    private String Name;
    private String AccessToken;

    public ResponseUserRegisterJson(String name, String accessToken) {
        Name = name;
        AccessToken = accessToken;
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
