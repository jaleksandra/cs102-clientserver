
package CS102PZServer;

/**
 *
 * @author Aleksandra
 */

import CS102PZCommon.UserList;
import java.io.IOException;

/**
 * ListUsersService je klasa koja se koristi za rukovanje LIST_USERS zahtevima
 */
public class ListUsersService extends Service {

    private UserList userList;

    /**
     * konstruktor klase ListUsersService
     */
    public ListUsersService() {
        handleRequest();
    }

    /**
     * handleRequest metoda koja obavlja celokupni posao
     */
    @Override
    protected void handleRequest() {
        try {
            userList = DBUtil.listUsersQuery();
            Server.outputStream.writeObject(userList);
            Server.outputStream.flush();
            System.out.println("Posalji listu korisnika ka: " + Server.socket.getInetAddress());
        } catch (IOException ex) {
            System.out.println("ListUsers IOException");
        }
    }
}

