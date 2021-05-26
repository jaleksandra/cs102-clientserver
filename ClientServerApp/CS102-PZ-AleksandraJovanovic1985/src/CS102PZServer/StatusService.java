
package CS102PZServer;

/**
 *
 * @author Aleksandra
 */

import CS102PZCommon.User;
import java.io.IOException;

/**
 * StatusService se koristi da rukuje STATUS zahtevima
 */

public class StatusService extends Service {

    User tempUser;

    /**
     * konstruktor klase StatusService 
     */
    public StatusService() {
        handleRequest();
    }

    /**
     * handleRequest metoda koja obavlja celokupni posao
     */
    @Override
    protected void handleRequest() {
        try {
            tempUser = (User) Server.inputStream.readObject();
            if (DBUtil.changeStatusQuery(tempUser)) {
                Server.outputStream.writeObject(Boolean.TRUE);
                Server.outputStream.flush();
                System.out.println("Status promenjen u : " + tempUser.getStatus());
            } else {
                Server.outputStream.writeObject(Boolean.FALSE);
                Server.outputStream.flush();
                System.out.println("Neuspela promena statusa!");
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Status Service IOException");
        }
    }

}

