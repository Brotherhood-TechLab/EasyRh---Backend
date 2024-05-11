package br.com.easyrh.domain.Entities;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import br.com.easyrh.domain.Entities.Base.ClassBase;
import br.com.easyrh.shered.request.address.RequestAddressRegisterJson;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="address")
public class Address extends ClassBase
{
    private static final long serialVersionUID = 1L;

    @Column(name="street", nullable = false, length = 100)
    private String Street;

    @Column(name="number", nullable = false, length = 16)
    private String Number;

    @Column(name="complement", length = 100)
    private String Complement;

    @Column(name="neighborhood", nullable = false, length = 50)
    private String Neighborhood;

    @Column(name="city", nullable = false, length = 20)
    private String City;

    @Column(name="state", nullable = false, length = 2)
    private String State;

    @Column(name="zip_code", nullable = false, length = 12)
    private String ZipCode;

    @Column(name="country", nullable = false, length = 3)
    private String Country;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Enterprise Enterprise;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Address() { }

    public Address(RequestAddressRegisterJson address) {
        // Chama o construtor da classe pai (BaseEntity) e configura suas propriedades
        super.setActive(true);
        super.setCreationDate(new Date());
        super.setModificationDate(new Date());
        super.setGuid_Identifier(UUID.randomUUID().toString());
        
        // Copia as propriedades do endereço fornecido para esta instância de endereço
        BeanUtils.copyProperties(address, this);
        
        // Define a data de modificação para a data atual
        this.setModificationDate(new Date());
        
        // Gera um identificador GUID único para esta instância de endereço
        this.setGuid_Identifier(UUID.randomUUID().toString());
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getNeighborhood() {
        return Neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        Neighborhood = neighborhood;
    }

    public String getComplement() {
        return Complement;
    }

    public void setComplement(String complement) {
        Complement = complement;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Street == null) ? 0 : Street.hashCode());
        result = prime * result + ((Number == null) ? 0 : Number.hashCode());
        result = prime * result + ((Complement == null) ? 0 : Complement.hashCode());
        result = prime * result + ((Neighborhood == null) ? 0 : Neighborhood.hashCode());
        result = prime * result + ((City == null) ? 0 : City.hashCode());
        result = prime * result + ((State == null) ? 0 : State.hashCode());
        result = prime * result + ((ZipCode == null) ? 0 : ZipCode.hashCode());
        result = prime * result + ((Country == null) ? 0 : Country.hashCode());
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
        Address other = (Address) obj;
        if (Street == null) {
            if (other.Street != null)
                return false;
        } else if (!Street.equals(other.Street))
            return false;
        if (Number == null) {
            if (other.Number != null)
                return false;
        } else if (!Number.equals(other.Number))
            return false;
        if (Complement == null) {
            if (other.Complement != null)
                return false;
        } else if (!Complement.equals(other.Complement))
            return false;
        if (Neighborhood == null) {
            if (other.Neighborhood != null)
                return false;
        } else if (!Neighborhood.equals(other.Neighborhood))
            return false;
        if (City == null) {
            if (other.City != null)
                return false;
        } else if (!City.equals(other.City))
            return false;
        if (State == null) {
            if (other.State != null)
                return false;
        } else if (!State.equals(other.State))
            return false;
        if (ZipCode == null) {
            if (other.ZipCode != null)
                return false;
        } else if (!ZipCode.equals(other.ZipCode))
            return false;
        if (Country == null) {
            if (other.Country != null)
                return false;
        } else if (!Country.equals(other.Country))
            return false;
        return true;
    }

}
