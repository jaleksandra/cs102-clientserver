
package CS102PZServer;

/**
 *
 * @author Aleksandra
 */

import CS102PZCommon.Message;
import java.io.IOException;

/**
 * SendMessageService je klasa koja rukuje zahtevima za slanje poruka
 */

public class SendMessageService extends Service {

    Message tempMessage;

    /**
     * konstruktor klase SendMessageService 
     */
    public SendMessageService() {
        handleRequest();
    }

    /**
     * handleRequest metoda koja obavlja celokupni posao
     */
    @Override
    protected void handleRequest() {
        try {
            tempMessage = (Message) Server.inputStream.readObject();
            if (DBUtil.sendMessageQuery(tempMessage)) {
                Server.outputStream.writeObject(Boolean.TRUE);
                Server.outputStream.flush();
                System.out.println("Poruka poslata od strane : " + tempMessage.getSenderUsername() + " ka: " + tempMessage.getRecipientUsername());
            } else {
                Server.outputStream.writeObject(Boolean.FALSE);
                Server.outputStream.flush();
                System.out.println(tempMessage.getSenderUsername() + " neuspesno slanje poruke: " + tempMessage.getRecipientUsername());
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("MessageService IOException");
        }
    }

}

