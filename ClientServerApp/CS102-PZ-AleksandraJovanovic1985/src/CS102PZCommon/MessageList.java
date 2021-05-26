
package CS102PZCommon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * MessageList klasa za slanje liste poruka kroz mrezu
 */

/**
 *
 * @author Aleksandra
 */
public class MessageList implements Serializable{

    private ArrayList<Message> messageList;
    private User owner;

    /**
     * MessageList konstruktor
     *
     */
    public MessageList(User user) {
        this.owner = user;
        this.messageList = new ArrayList<>();
    }

    /**
     * getter metoda za listu poruka
     *
     * vraca listu poruka
     */
    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    /**
     * setter metoda za listu poruka
     *
     * @param messageList lista poruka
     */
    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    /**
     * getter metoda za vlasnika
     *
     * vraca vlasnika
     */
    public User getOwner() {
        return owner;
    }

    /**
     * setter metoda za vlasnika
     *
     * @param owner vlasnik
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * predefinisanje toString metode vraca tekstualnu reprezentaciju liste poruka 
     *
     * @return
     */
    @Override
    public String toString() {
        return "MessageList{" + "messageList=" + messageList + ", owner=" + owner + '}';
    }
    
}
