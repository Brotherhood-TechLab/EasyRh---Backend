package br.com.easyrh.shared.request.user;

import java.io.Serializable;

import br.com.easyrh.shared.request.address.RequestAddressRegisterJson;

public class RequestUserRegisterJson implements Serializable {

  private static final long serialVersionUID = 1L;

  private String Name;

  private String Email;

  private String Password;

  private String Cpf;

  private String Dateofbirth;

  private String Gender;

  private String Phone;

  private String EnterpriseNumber;

  private boolean Role;

  private RequestAddressRegisterJson Address;

  public RequestUserRegisterJson(String Cpf, String Dateofbirth, String Email,
      String EnterpriseNumber, String Gender, String Name, String Password,
      String Phone, boolean Role, RequestAddressRegisterJson Address) {
    this.Cpf = Cpf;
    this.Dateofbirth = Dateofbirth;
    this.Email = Email;
    this.EnterpriseNumber = EnterpriseNumber;
    this.Gender = Gender;
    this.Name = Name;
    this.Password = Password;
    this.Phone = Phone;
    this.Role = Role;
    this.Address = Address;
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

  public String getEnterpriseNumber() {
    return EnterpriseNumber;
  }

  public boolean isRole() {
    return Role;
  }

  public void setRole(boolean role) {
    Role = role;
  }

  public void setEnterpriseNumber(String EnterpriseNumber) {
    this.EnterpriseNumber = EnterpriseNumber;
  }

  public RequestAddressRegisterJson getAddress() {
    return Address;
  }

  public void setAddress(RequestAddressRegisterJson address) {
    Address = address;
  }

  public boolean getRole() {
    return Role;
  }

}
