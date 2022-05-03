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
}
