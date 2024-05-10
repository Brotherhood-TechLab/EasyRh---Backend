package br.com.easyrh.shered.request.Person;

import java.io.Serializable;
import java.util.Date;

public class RequestPersonRegisterJson implements  Serializable
{

    private static final long serialVersionUID = 1L;

    private String Name;

    private String Email;

    private String Password;

    private String Cpf;

    private Date Dateofbirth;

    private String Gender;

    private String Phone;

    private String EnterpriseNumber;

    public RequestPersonRegisterJson(String Cpf, Date Dateofbirth, String Email, String EnterpriseNumber, String Gender, String Name, String Password, String Phone) {
        this.Cpf = Cpf;
        this.Dateofbirth = Dateofbirth;
        this.Email = Email;
        this.EnterpriseNumber = EnterpriseNumber;
        this.Gender = Gender;
        this.Name = Name;
        this.Password = Password;
        this.Phone = Phone;
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

    public String getEnterpriseNumber() {
        return EnterpriseNumber;
    }

    public void setEnterpriseNumber(String EnterpriseNumber) {
        this.EnterpriseNumber = EnterpriseNumber;
    }  
}
