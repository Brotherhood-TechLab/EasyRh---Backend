package br.com.easyrh.domain.Entities;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import br.com.easyrh.domain.Entities.Base.ClassBase;
import br.com.easyrh.shered.request.enterprise.RequestEnterpriseRegisterJson;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity //Sempre usar o jarkart persistence - Indidcando que isso representa uma tabela do banco de dados
@Table(name="enterprise")//Nome da tabela no banco 
public class Enterprise extends ClassBase implements Serializable 
{
    private static final long serialVersionUID = 1L;

    @Column(name="name", nullable = false, length = 100) 
    private String Name;

    @Column(name="cnpj", nullable = false, length = 16)
    private String Cnpj;

    @Column(name="phone_number", nullable = false, length = 16)
    private String PhoneNumber;

    @Column(name="email", nullable = false, length = 50)
    private String Email;

    @Column(name = "IdNumber")
    private String IdNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    
    @Column(name="logo_image")
    private String LogoImage;

    @OneToOne(mappedBy = "enterprise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Person Person;

    public Enterprise(RequestEnterpriseRegisterJson enterprise)
    {
        super();
        BeanUtils.copyProperties(enterprise, this);
        this.address = new Address(enterprise.address);
    }

    public Enterprise() { }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLogoImage() {
        return LogoImage;
    }

    public void setLogoImage(String logoImage) {
        LogoImage = logoImage;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String IdNumber) {
        this.IdNumber = IdNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Name == null) ? 0 : Name.hashCode());
        result = prime * result + ((Cnpj == null) ? 0 : Cnpj.hashCode());
        result = prime * result + ((PhoneNumber == null) ? 0 : PhoneNumber.hashCode());
        result = prime * result + ((Email == null) ? 0 : Email.hashCode());
        result = prime * result + ((LogoImage == null) ? 0 : LogoImage.hashCode());
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
        Enterprise other = (Enterprise) obj;
        if (Name == null) {
            if (other.Name != null)
                return false;
        } else if (!Name.equals(other.Name))
            return false;
        if (Cnpj == null) {
            if (other.Cnpj != null)
                return false;
        } else if (!Cnpj.equals(other.Cnpj))
            return false;
        if (PhoneNumber == null) {
            if (other.PhoneNumber != null)
                return false;
        } else if (!PhoneNumber.equals(other.PhoneNumber))
            return false;
        if (Email == null) {
            if (other.Email != null)
                return false;
        } else if (!Email.equals(other.Email))
            return false;
        if (LogoImage == null) {
            if (other.LogoImage != null)
                return false;
        } else if (!LogoImage.equals(other.LogoImage))
            return false;
        return true;
    }    
}
