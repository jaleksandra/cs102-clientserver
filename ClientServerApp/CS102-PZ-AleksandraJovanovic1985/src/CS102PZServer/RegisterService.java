
package CS102PZServer;

/**
 *
 * @author Aleksandra
 */

import CS102PZCommon.User;
import java.io.IOException;

/**
 * RegisterService je klasa koja rukuje zahtevima za registraciju
 */
public class RegisterService extends Service {

    User tempUser;

    /**
     * konstruktor klase RegisterService 
     */
    public RegisterService() {
        handleRequest();
    }

    /**
     * handleRequest metoda koja obavlja celokupni posao
     */
    @Override
    protected void handleRequest() {
        try {
            tempUser = (User) Server.inputStream.readObject();
            if (DBUtil.registerUserQuery(tempUser)) {
                Server.outputStream.writeObject(Boolean.TRUE);
                Server.outputStream.flush();
                System.out.println("Uspesna registracija od strane " + Server.socket.getInetAddress());
            } else {
                Server.outputStream.writeObject(Boolean.FALSE);
                Server.outputStream.flush();
                System.out.println("Neuspesna registracija od strane " + Server.socket.getInetAddress());
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Register Thread IOException");
        }
    }

}

