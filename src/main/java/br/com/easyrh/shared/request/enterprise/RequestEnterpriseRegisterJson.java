package br.com.easyrh.shared.request.enterprise;

import java.io.Serializable;

import br.com.easyrh.shared.request.address.RequestAddressRegisterJson;

public class RequestEnterpriseRegisterJson implements Serializable {
  private static final long serialVersionUID = 1L;

  public String name;
  public String idNumber;
  public String cnpj;
  public String phoneNumber;
  public String email;
  public RequestAddressRegisterJson address;
  public String logoImage; // Refinar futuramente

  public RequestEnterpriseRegisterJson(String idNumber, RequestAddressRegisterJson address, String cnpj, String email,
      String logoImage, String name, String phoneNumber) {
    this.idNumber = idNumber;
    this.address = address;
    this.cnpj = cnpj;
    this.email = email;
    this.logoImage = logoImage;
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
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

  public RequestAddressRegisterJson getAddress() {
    return address;
  }

  public void setAddress(RequestAddressRegisterJson address) {
    this.address = address;
  }

  public String getLogoImage() {
    return logoImage;
  }

  public void setLogoImage(String logoImage) {
    this.logoImage = logoImage;
  }
}
