
package CS102PZServer;

/**
 *
 * @author Aleksandra
 */

import CS102PZCommon.Status;
import CS102PZCommon.User;
import java.io.IOException;

/**
 * LoginService je klasa koja se koristi za rukovanje zahtevima za prijavljivanje
 */
public class LoginService extends Service {

    User tempUser;

    /**
     * LoginService konstruktor
     */
    public LoginService() {
        handleRequest();
    }

    /**
     * handleRequest metoda koja obavlja celokupni posao
     */
    @Override
    protected void handleRequest() {
        try {
            tempUser = (User) Server.inputStream.readObject();
            if (DBUtil.loginUserQuery(tempUser)) {
                Server.outputStream.writeObject(Boolean.TRUE);
                Server.outputStream.flush();
                tempUser.setStatus(Status.ONLINE);
                DBUtil.changeStatusQuery(tempUser);
                System.out.println("Uspesno prijavljivanje od strane " + Server.socket.getInetAddress());
            } else {
                Server.outputStream.writeObject(Boolean.FALSE);
                Server.outputStream.flush();
                System.out.println("Neuspesno prijavljivanje od strane " + Server.socket.getInetAddress());
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("LoginThread IOException");
        }
    }
}

