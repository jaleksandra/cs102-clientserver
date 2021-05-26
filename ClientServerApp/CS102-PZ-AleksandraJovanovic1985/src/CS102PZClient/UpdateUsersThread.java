
package CS102PZClient;

import static CS102PZClient.MessengerStage.openChats;
import static CS102PZClient.MessengerStage.usersVBox;
import CS102PZCommon.User;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Aleksandra
 */

/**
 * UpdateUsersThread se koristi da azurira listu korisnika(UserList) u MessengerStage
 */

public class UpdateUsersThread extends Thread {
    
    private boolean running;

    /**
     * run metoda predefinise logiku
     */
    @Override
    public void run() {
        running = true;
        while (running) {
            Platform.runLater(() -> {
                MessengerStage.usersVBox.getChildren().clear();
                MessengerStage.usersList = CmdUtil.sendListUsers().getUserList();
                MessengerStage.usersList.stream().forEach((User user) -> {
                    if (user.getUsername().equals(MessengerStage.getCurrentUser().getUsername())) {
                        /**
                         * Ne radi nista
                         */
                    } else {
                        HBox userHBox = new HBox();
                        userHBox.setStyle("-fx-spacing: 200;-fx-background-color: #FFFFFF;");
                        userHBox.setMinWidth(MessengerStage.MIN_SIZE);
                        userHBox.setMaxWidth(MessengerStage.MIN_SIZE);
                        usersVBox.getChildren().addAll(userHBox);
                        Label usernameLbl = new Label(user.getUsername());
                        Label statusLbl = new Label(user.getStatus().toString());
                        userHBox.getChildren().addAll(usernameLbl, statusLbl);

                        userHBox.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                            ChatWindow chatWindow = new ChatWindow(user);
                            openChats.add(chatWindow);
                            event.consume();

                        });
                    }
                });
            });
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println("UpdateUsersThread InterruptedException");
            }
        }
    }

    /**
     * isRunning metoda proverava da li je nit aktivna
     *
     * vraca da je aktivna
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * stopRunning metoda zaustavlja nit
     */
    public void stopRunning() {
        this.running = false;
    }
    
}
