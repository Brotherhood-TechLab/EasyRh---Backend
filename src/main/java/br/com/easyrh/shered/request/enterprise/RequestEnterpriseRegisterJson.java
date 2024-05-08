package br.com.easyrh.shered.request.enterprise;

import java.io.Serializable;
import java.util.Objects;

import br.com.easyrh.shered.request.address.RequestAddressRegisterJson;

public class RequestEnterpriseRegisterJson implements Serializable  {

    private static final long serialVersionUID = 1L;

    public String name;
    public String cnpj;
    public String phoneNumber;
    public String email;
    public RequestAddressRegisterJson address;
    public String logoImage;
    
    public RequestEnterpriseRegisterJson(String name, String cnpj, String phoneNumber, String email,
        RequestAddressRegisterJson address, String logoImage) {
        this.name = name;
        this.cnpj = cnpj;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.logoImage = logoImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public RequestAddressRegisterJson getAddress() {
        return address;
    }

    public void setAddress(RequestAddressRegisterJson address) {
        this.address = address;
    }

    public String getStreet() {
        if (address != null) {
            return address.getStreet();
        }
        return null;
    }

    public String getNumber() {
        if (address != null) {
            return address.getNumber();
        }
        return null;
    }

    public String getNeighborhood() {
        if (address != null) {
            return address.getNeighborhood();
        }
        return null;
    }

    public String getComplement() {
        if (address != null) {
            return address.getComplement();
        }
        return null;
    }

    public String getCity() {
        if (address != null) {
            return address.getCity();
        }
        return null;
    }

    public String getState() {
        if (address != null) {
            return address.getState();
        }
        return null;
    }

    public String getZipCode() {
        if (address != null) {
            return address.getZipCode();
        }
        return null;
    }

    public String getCountry() {
        if (address != null) {
            return address.getCountry();
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.cnpj);
        hash = 67 * hash + Objects.hashCode(this.phoneNumber);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.logoImage);
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
        final RequestEnterpriseRegisterJson other = (RequestEnterpriseRegisterJson) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.logoImage, other.logoImage);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
