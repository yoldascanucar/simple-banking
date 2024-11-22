package session_manager;
import entity.ClientInformation;

import java.util.List;

public class SessionManager  {

    private static ClientInformation loggedInClientInformation;

    public static void login(ClientInformation clientInformation) {
        loggedInClientInformation = clientInformation;
    }

    public static ClientInformation getLoggedInClientInformation() {
        return loggedInClientInformation;
    }
}
