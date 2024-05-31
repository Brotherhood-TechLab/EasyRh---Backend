package br.com.easyrh.domain.Entities;

import br.com.easyrh.domain.Entities.Base.ClassBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department extends ClassBase {

  private String Name;

  private String Description;

  @ManyToOne(cascade = CascadeType.ALL)
  private Enterprise Enterprise;

  public Department() {

  }

  public Department(String name, String description, Enterprise enterprise) {
    super();
    this.Name = name;
    this.Description = description;
    this.Enterprise = enterprise;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public Enterprise getEnterprise() {
    return Enterprise;
  }

  public void setEnterprise(Enterprise enterprise) {
    Enterprise = enterprise;
  }
}
