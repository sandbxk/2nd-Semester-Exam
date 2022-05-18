package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLColumn;
import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

@SQLTable(name = "Account")
public class Account {

    @SQLColumn(name = "AID")
    private int id;

    @SQLColumn(name = "username")
    private String username;

    @SQLColumn(name = "hashed_pwd")
    private String password;

    @SQLColumn(name = "firstName")
    private String firstName;

    @SQLColumn(name = "lastName")
    private String lastName;

    @SQLColumn(name = "email")
    private String email;

    @SQLColumn(name = "FK_AccountSchool")
    private School school;

    @SQLColumn(name = "privilegeLevel")
    private int authorization;

    public Account(int id, String login, String password, String firstName, String lastName, String email, School school, int authorization) {
        this.id = id;
        this.username = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.school = school;
        this.authorization = authorization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName()
    {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) { this.password = password;}

    public String getPassword(){ return password;}

    public void setUsername(String username) { this.username = username;}

    public String getUsername(){return username;}

    public void setAuthorization(int authorization){ this.authorization = authorization;}

    public int getAuthorization(){ return authorization;}

    public void setSchool(School school){this.school = school;}

    public School getSchool(){return school;}


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
