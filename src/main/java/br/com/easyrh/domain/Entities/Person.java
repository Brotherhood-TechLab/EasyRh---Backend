package br.com.easyrh.domain.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.easyrh.domain.Entities.Base.ClassBase;
import br.com.easyrh.domain.Enum.Role;
import br.com.easyrh.shered.request.Person.RequestPersonRegisterJson;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person extends ClassBase implements Serializable, UserDetails
{
    private static final long serialVersionUID = 1L;

    @Column(name="name", nullable = false, length = 150)
    private String Name;

    @Column(name="email", nullable = false, length = 150)
    private String Email;

    @Column(name="password", nullable = false, length = 2000)
    private String Password;

    @Column(name="cpf", nullable = false, length = 14)
    private String Cpf;

    @Column(name="date_birth", nullable = false)
    private Date Dateofbirth;

    @Column(name="gender", nullable = false, length = 1)
    private String Gender;

    @Column(name="phoneNumber", nullable = false, length = 16)
    private String Phone;

    private Role Role;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "payment_id")
    // private Payment Payment;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "role_id")
    // private Role Role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address Address;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    public Person(RequestPersonRegisterJson person) {
        BeanUtils.copyProperties(person, this);
    }

    public Person() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public Date getDateofbirth() {
        return Dateofbirth;
    }

    public void setDateofbirth(Date Dateofbirth) {
        this.Dateofbirth = Dateofbirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Fazendo a validação do role
        if(this.Role == Role.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.Email;
    }

    @Override
    public boolean isAccountNonExpired() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAccountNonLocked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
