package br.com.easyrh.domain.Entities;

import br.com.easyrh.domain.Entities.Base.ClassBase;
import ch.qos.logback.classic.Level;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department extends ClassBase {

  private String Name;

  private String Description;

  private Level Level;

  @ManyToOne(cascade = CascadeType.ALL)
  private Enterprise Enterprise;

  Department() {

  }

  Department(String name, String description, Level level, Enterprise enterprise) {
    this.Name = name;
    this.Description = description;
    this.Level = level;
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

  public Level getLevel() {
    return Level;
  }

  public void setLevel(Level level) {
    Level = level;
  }

  public Enterprise getEnterprise() {
    return Enterprise;
  }

  public void setEnterprise(Enterprise enterprise) {
    Enterprise = enterprise;
  }
}
