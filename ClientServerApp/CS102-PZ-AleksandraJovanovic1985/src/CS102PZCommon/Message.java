
package CS102PZCommon;

import java.io.Serializable;

/**
 *
 * @author Aleksandra
 */

/**
 * Message koristi se da skladisti podatke o tekstu poruka
 */

public class Message implements Serializable {

    private long messageID;
    private String senderUsername;
    private String recipientUsername;
    private String messageText;

    /**
     * Message konstruktor
     *
     * @param senderUsername korisnicko ime posiljaoca
     * @param recipientUsername korisnicko ime primaoca
     * @param messageText tekst poruke
     */
    public Message(String senderUsername, String recipientUsername, String messageText) {
        this.messageID = 0;
        this.senderUsername = senderUsername;
        this.recipientUsername = recipientUsername;
        this.messageText = messageText;
    }

    /**
     * getter za ID poruke
     *
     * vraca ID poruke
     */
    public long getMessageID() {
        return messageID;
    }

    /**
     * setter za ID poruke
     *
     * @param messageID setuje ID poruke
     */
    public void setMessageID(long messageID) {
        this.messageID = messageID;
    }

    /**
     * getter za tekst poruke
     *
     * vraca tekst poruke
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * setter za tekst poruke
     *
     * @param messageText setuje tekst poruke
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * getter za korisnicko ime posiljaoca
     *
     * vraca korisnicko ime posiljaoca
     */
    public String getSenderUsername() {
        return senderUsername;
    }

    /**
     * setter za korisnicko ime posiljaoca
     *
     * @param senderUsername korisnicko ime posiljaoca
     */
    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    /**
     * getter za korisnicko ime primaoca
     *
     * vraca korisnicko ime primaoca
     */
    public String getRecipientUsername() {
        return recipientUsername;
    }

    /**
     * setter za korisnicko ime primaoca
     *
     * @param recipientUsername korisnicko ime primaoca
     */
    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    /**
     * predefinisanje toString metode
     *
     * vraca tekstualnu reprezentaciju objekta
     */
    @Override
    public String toString() {
        return "Message{" + "id=" + messageID + ", senderUsername=" + senderUsername + ", recipientUsername=" + recipientUsername + ", message=" + messageText + '}';
    }

    
}
