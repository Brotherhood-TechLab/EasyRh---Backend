package br.com.easyrh.shared.request.address;

import java.io.Serializable;
import java.util.Objects;

public class RequestAddressRegisterJson implements Serializable {
  private static final long serialVersionUID = 1L;

  private String street;
  private String number;
  private String neighborhood;
  private String complement;
  private String city;
  private String state;
  private String zipCode;
  private String country;

  public RequestAddressRegisterJson(String street, String number, String neighborhood, String complement, String city,
      String state,
      String zipCode, String country) {
    this.street = street;
    this.number = number;
    this.neighborhood = neighborhood;
    this.complement = complement;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.country = country;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(String complement) {
    this.complement = complement;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 37 * hash + Objects.hashCode(this.street);
    hash = 37 * hash + Objects.hashCode(this.number);
    hash = 37 * hash + Objects.hashCode(this.neighborhood);
    hash = 37 * hash + Objects.hashCode(this.city);
    hash = 37 * hash + Objects.hashCode(this.state);
    hash = 37 * hash + Objects.hashCode(this.zipCode);
    hash = 37 * hash + Objects.hashCode(this.country);
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
    final RequestAddressRegisterJson other = (RequestAddressRegisterJson) obj;
    if (!Objects.equals(this.street, other.street)) {
      return false;
    }
    if (!Objects.equals(this.number, other.number)) {
      return false;
    }
    if (!Objects.equals(this.neighborhood, other.neighborhood)) {
      return false;
    }
    if (!Objects.equals(this.city, other.city)) {
      return false;
    }
    if (!Objects.equals(this.state, other.state)) {
      return false;
    }
    if (!Objects.equals(this.zipCode, other.zipCode)) {
      return false;
    }
    return Objects.equals(this.country, other.country);
  }
}
