package Application.BE;

public record Teacher(int id, String login, String password, String firstName, String surname, String email, String salt, int schoolid) {
}
