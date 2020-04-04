package pl.rapid.data;

import pl.rapid.data.model.LoggedInUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private static final List DUMMY_CREDENTIALS = Arrays.asList("lab3@zut.pl", "root");

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            if (DUMMY_CREDENTIALS.contains(username) && DUMMY_CREDENTIALS.contains(password)) {
                LoggedInUser fakeUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                username);
                return new Result.Success<>(fakeUser);
            } else {
                return new Result.Error(new IOException("Incorrect credentials!"));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
