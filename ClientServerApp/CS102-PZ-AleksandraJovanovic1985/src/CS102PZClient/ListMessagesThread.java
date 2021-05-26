
package CS102PZClient;

import CS102PZCommon.Message;
import CS102PZCommon.MessageList;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author Aleksandra
 */

/**
 * ListMessagesThread je nit koja azurira poruke u prozoru za komunikaciju
 */

public class ListMessagesThread extends Thread {
    
    private boolean running;
    private MessageList msgL;

    /**
     * run metoda predefinise celokupnu logiku
     */
    @Override
    public void run() {
        running = true;
        while (running) {
            msgL = CmdUtil.sendListMessages(MessengerStage.getCurrentUser());
            Platform.runLater(() -> {
                ChatWindow.porukeVBox.getChildren().clear();
                msgL.getMessageList().stream().forEach((Message msg) -> {
                    Label tmpLabel = new Label();
                    if (msg.getSenderUsername().equals(MessengerStage.getCurrentUser().getUsername())) {
                        tmpLabel.setText(msg.getMessageText());
                        tmpLabel.setStyle("-fx-background-color: #d6d0c4;");
                        tmpLabel.setAlignment(Pos.CENTER_RIGHT);
                    } else {
                        tmpLabel.setText(msg.getMessageText());
                        tmpLabel.setStyle("-fx-background-color: #00bfff;");
                        tmpLabel.setAlignment(Pos.CENTER_LEFT);
                    }
                    ChatWindow.porukeVBox.getChildren().add(tmpLabel);
                });
            });
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("ListMessagesThread prekinuta");
            }
        }
    }

    /**
     * isRunning metoda koja proverava da li je nit aktivna
     *
     * @vraca da je nit aktivna
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * stopRunning metoda prekida nit
     */
    public void stopRunning() {
        this.running = false;
    }
    
}
