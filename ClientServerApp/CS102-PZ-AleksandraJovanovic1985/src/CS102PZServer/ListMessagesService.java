
package CS102PZServer;

/**
 *
 * @author Aleksandra
 */

import CS102PZCommon.MessageList;
import CS102PZCommon.User;
import java.io.IOException;

/**
 * ListMessagesService je klasa koja rukuje GET_MESSAGES zahtevima
 */

class ListMessagesService extends Service {

    private User tempUser;
    private MessageList messageList;

    /**
     * konstruktor klase ListMessagesService 
     */
    public ListMessagesService() {
        handleRequest();
    }

    /**
     * handleRequest metoda koja obavlja celokupan posao
     */
    @Override
    protected void handleRequest() {
        try {
            tempUser = (User) Server.inputStream.readObject();
            messageList = DBUtil.listMessagesQuery(tempUser);
            Server.outputStream.writeObject(messageList);
            Server.outputStream.flush();
            System.out.println("Poruke uspesno poslate ka: " + tempUser.getUsername());
        } catch (IOException e) {
            System.out.println("Neuspesno slanje!");

        } catch (ClassNotFoundException ex) {
            System.out.println("losa klasa");
        }
    }

}

