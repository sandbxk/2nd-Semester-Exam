package Application.GUI.Models;

import Application.BLL.AccountManager;
import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.LongPasswordStrategy;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HexFormat;


import Application.BE.Account;
import Application.BE.School;
import Application.BLL.AdminDataManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AccountModel
{
    public UserType type;

    private AccountManager accountManager;

    private String username = null;
    private String accessToken = null;

    public AccountModel(String username, String raw_password, UserType type)
    {
        accountManager = new AccountManager();

        this.accessToken = generateAccessToken(username, raw_password);
        this.username = username;
    }
  
      private AdminDataManager adminDataManager = new AdminDataManager();

    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private Account student;

    ObservableList<Account> accounts;

    public AccountModel(Account student) : this() {
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

    /**
     * @param username .
     * @param password .
     * @return a base64 encoded hash string composed of the raw password and the username (as salt)
     * */
    private String generateAccessToken(String username, String password)
    {
        var hashed = BCrypt.with(BCrypt.Version.VERSION_2Y).hash(BCrypt.MIN_COST, extendStringToLength(username, 16).getBytes(), password.getBytes(StandardCharsets.UTF_8));

        return new String(Base64.getEncoder().encode(hashed));
    }

    /**
     * @param input the original string that needs to be expanded
     * @param length the desired length of the result
     * @return the input repeated until the length of the string equals the length parameter
     * */
    private String extendStringToLength(String input, int length)
    {
        int cycles = (length + 1) / Math.max(1, input.length()) + 1;

        String result = input.repeat(cycles);

        return result.substring(0, Math.min(result.length(), length));
    }


    public boolean authenticate()
    {
        Object user = accountManager.authenticate(this.username, this.accessToken);

        return user != null;
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
