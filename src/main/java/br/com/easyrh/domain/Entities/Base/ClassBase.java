package br.com.easyrh.domain.Entities.Base;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class ClassBase implements  Serializable
{
    private static final long serialVersionUID = 1L;

    @Id //Auto increment
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @Column(name="create_date")
    private Date CreationDate;

    @Column(name="active")
    private Boolean Active;

    @Column(name="Guid_Identifier")  
    private String Guid_Identifier;

    @Column(name = "modification_date")
    private Date ModificationDate;
    
    public ClassBase() 
    {
        this.CreationDate = new Date();
        this.Active = true;
        this.Guid_Identifier = UUID.randomUUID().toString();
        this.ModificationDate = new Date();
    }

    public ClassBase(Long id, Date creationDate, Boolean active, String guid_Identifier, Date modificationDate) {
        this.Id = id;
        this.CreationDate = creationDate;
        this.Active = active;
        this.Guid_Identifier = guid_Identifier;
        this.ModificationDate = modificationDate;
    }


    public Long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public Boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public String getGuid_Identifier() {
        return Guid_Identifier;
    }

    public void setGuid_Identifier(String Guid_Identifier) {
        Guid_Identifier = Guid_Identifier;
    }    

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean Active) {
        this.Active = Active;
    }

    public Date getModificationDate() {
        return ModificationDate;
    }

    public void setModificationDate(Date ModificationDate) {
        this.ModificationDate = ModificationDate;
    }

}
