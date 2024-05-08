package br.com.easyrh.shered.response.employee;

import java.util.Objects;

public class ResponseEnterpriseRegisterJson {

    public String name;
    public String accessToken;
    
    public ResponseEnterpriseRegisterJson(String name, String accessToken) {
        this.name = name;
        this.accessToken = accessToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.accessToken);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResponseEnterpriseRegisterJson other = (ResponseEnterpriseRegisterJson) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.accessToken, other.accessToken);
    }

}
