package br.com.easyrh.shered.response.enterprise;

import java.util.Objects;

public class ResponseEnterpriseRegisterJson {

    public String name;
    public String enterpriseIdentifier;
    
    public ResponseEnterpriseRegisterJson(String name, String identifier) {
        this.name = name;
        this.enterpriseIdentifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEnterpriseIdentifier() {
        return enterpriseIdentifier;
    }

    public void setEnterpriseIdentifier(String enterpriseIdentifier) {
        this.enterpriseIdentifier = enterpriseIdentifier;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.enterpriseIdentifier);
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
        return Objects.equals(this.enterpriseIdentifier, other.enterpriseIdentifier);
    }
}
