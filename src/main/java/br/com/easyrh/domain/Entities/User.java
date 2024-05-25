package br.com.easyrh.domain.Entities;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.easyrh.domain.Entities.Base.ClassBase;
import br.com.easyrh.domain.Enum.Role;
import br.com.easyrh.shared.request.user.RequestUserEditJson;
import br.com.easyrh.shared.request.user.RequestUserRegisterJson;
import br.com.easyrh.shared.response.user.ResponseUserRepresentation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User extends ClassBase implements UserDetails {
  private static final long serialVersionUID = 1L;

  @Column(name = "name", nullable = false, length = 150)
  private String Name;

  @Column(name = "email", nullable = false, length = 150)
  private String Email;

  @Column(name = "password", nullable = false, length = 2000)
  private String Password;

  @Column(name = "cpf", nullable = false, length = 14)
  private String Cpf;

  @Column(name = "date_birth", nullable = false)
  private String Dateofbirth;

  @Column(name = "gender", nullable = false, length = 1)
  private String Gender;

  @Column(name = "phoneNumber", nullable = false, length = 16)
  private String Phone;

  // @OneToOne(fetch = FetchType.LAZY)
  // @JoinColumn(name = "payment_id")
  // private Payment Payment;

  @Column(name = "role")
  private Role Role;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "address_id")
  private Address Address;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "enterprise_id", nullable = true)
  private Enterprise Enterprise;

  public User(RequestUserRegisterJson user) {
    super();

    BeanUtils.copyProperties(user, this);

    this.Address = new Address(user.getAddress());
  }

  public User(RequestUserEditJson user) {
    super();

    BeanUtils.copyProperties(user, this);

    this.Address = new Address(user.Address());
  }

  public User() {
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

  public String getDateofbirth() {
    return Dateofbirth;
  }

  public void setDateofbirth(String Dateofbirth) {
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

  public Role getRole() {
    return Role;
  }

  public Address getAddress() { return Address; }

  public void setAddress(Address address) { Address = address; }

  public void setRole(Role role) {
    Role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // Fazendo a validação do role
    if (this.Role == br.com.easyrh.domain.Enum.Role.ADMIN)
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return this.Email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public ResponseUserRepresentation toDTO() {
    return new ResponseUserRepresentation(Name, Email, Password, Cpf, Dateofbirth, Gender, Phone, "PREENCHER",
        Role.getBoolRole(), Address.toDTO());
  }
}
