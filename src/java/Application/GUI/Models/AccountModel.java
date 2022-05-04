package Application.GUI.Models;

import Application.BE.Account;
import Application.BE.School;
import Application.BLL.AdminDataManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountModel {

    private AdminDataManager adminDataManager = new AdminDataManager();

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private Account student;

    ObservableList<Account> accounts;

    public AccountModel(Account student) {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.email = new SimpleStringProperty();

        firstName.set(student.getFirstName());
        lastName.set(student.getLastName());
        email.set(student.getEmail());
        this.student = student;
    }

    public AccountModel() {
        accounts = FXCollections.observableArrayList();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName.set(newFirstName);

    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void createAccount(String text, String text1, String text2, String text3, String text4, School school, int i)
    {
           var account = adminDataManager.createAccount(text, text1, text2, text3, text4, school, i);
           accounts.add(account);
    }
}
