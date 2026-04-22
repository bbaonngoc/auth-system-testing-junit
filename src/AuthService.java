import java.util.HashMap;

public class AuthService {
    private HashMap<String, User> users = new HashMap<>();

    public boolean register(String username, String password) {
        if (username.length() < 6 || username.length() > 20) return false;
        if (password.length() < 6 || password.length() > 25) return false;
        if (users.containsKey(username)) return false;

        users.put(username, new User(username, password));
        return true;
    }

    public String login(String username, String password) {
        if (!users.containsKey(username)) return "USER_NOT_FOUND";

        User user = users.get(username);

        if (user.isLocked) return "ACCOUNT_LOCKED";

        if (user.password.equals(password)) {
            user.failedAttempts = 0;
            return "LOGIN_SUCCESS";
        } else {
            user.failedAttempts++;
            if (user.failedAttempts >= 5) {
                user.isLocked = true;
                return "ACCOUNT_LOCKED";
            }
            return "WRONG_PASSWORD";
        }
    }
}