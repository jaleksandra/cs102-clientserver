
package CS102PZClient;

import CS102PZCommon.Status;
import CS102PZCommon.User;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * MessengerStage je klasa koja omogucava prikazivanje nove pozornice u aplikaciji, kao i 
 * listu korisnika i nekih njihovih podesavanja
 */

/**
 *
 * @author Aleksandra
 */
public class MessengerStage extends Stage {
    
    public static final int MIN_SIZE = 300;
    private static User currentUser;
    public static ArrayList<User> usersList;
    public static VBox usersVBox;
    public static ArrayList<ChatWindow> openChats;
    private UpdateUsersThread updateUserList;

    /**
     * MessengerStage konstruktor klase
     *
     * 
     */
    public MessengerStage(User user) {
        updateUserList = new UpdateUsersThread();
        openChats = new ArrayList<>();
        currentUser = user;
        usersVBox = new VBox();
        usersVBox.autosize();
        VBox messengerRootVBox = new VBox();
        MenuBar menuBar = new MenuBar();
        Menu menuUser = new Menu(currentUser.getUsername());
        MenuItem onlineMI = new MenuItem("Aktivan");
        MenuItem awayMI = new MenuItem("Odsutan");
        MenuItem offlineMI = new MenuItem("Neaktivan");
        MenuItem separatorMI = new MenuItem("-----------");
        separatorMI.setDisable(true);
        MenuItem logoutMI = new MenuItem("Odjavljivanje");
        MenuItem quitMI = new MenuItem("Izadji");
        Menu menuStatus = new Menu(currentUser.getStatus().toString());
        menuStatus.setDisable(true);
        ScrollPane usersScrollPane = new ScrollPane(usersVBox);
        usersScrollPane.setMinHeight(MIN_SIZE);
        usersScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Scene messengerScene = new Scene(messengerRootVBox, 300, 300);

        menuUser.getItems().addAll(onlineMI, awayMI, offlineMI, separatorMI, logoutMI, quitMI);

        menuBar.getMenus().addAll(menuUser, menuStatus);

        messengerRootVBox.getChildren().addAll(menuBar, usersScrollPane);
        this.setScene(messengerScene);
        this.setResizable(false);
        this.show();
        updateUserList.start();
        /**
         * aktivan
         */
        onlineMI.setOnAction((ActionEvent event) -> {
            currentUser.setStatus(Status.ONLINE);
            CmdUtil.sendStatusChange(currentUser);
            menuStatus.setText(currentUser.getStatus().toString());
            event.consume();
        });
        /**
         * odsutan
         */
        awayMI.setOnAction((ActionEvent event) -> {
            currentUser.setStatus(Status.AWAY);
            CmdUtil.sendStatusChange(currentUser);
            menuStatus.setText(currentUser.getStatus().toString());
            event.consume();
        });
        /**
         * neaktivan
         */
        offlineMI.setOnAction((ActionEvent event) -> {
            currentUser.setStatus(Status.OFFLINE);
            CmdUtil.sendStatusChange(currentUser);
            menuStatus.setText(currentUser.getStatus().toString());
            event.consume();
        });
        /**
         * odjavljivanje
         */
        logoutMI.setOnAction(
                (ActionEvent event) -> {

                    currentUser.setStatus(Status.OFFLINE);
                    if (CmdUtil.sendStatusChange(currentUser).equals(Boolean.TRUE)) {
                        System.out.println("Odjavljivanje uspesno!");
                        updateUserList.stopRunning();
                        CS102PZ.pStage.show();
                        this.close();
                    } else {
                        System.out.println("Nemoguce odjavljivanje!");
                    }
                    event.consume();
                }
        );
        /**
         * Izadji
         */
        quitMI.setOnAction((ActionEvent event) -> {
            currentUser.setStatus(Status.OFFLINE);
            updateUserList.stopRunning();
            if (CmdUtil.sendStatusChange(currentUser).equals(Boolean.TRUE)) {
                System.out.println("Odjavljivanje uspesno!");
                Platform.exit();
            } else {
                System.out.println("Nemoguce odjavljivanje!");
                Platform.exit();
            }
            event.consume();
        });
        /**
         * Zahtev za odjavljivanjem
         */
        this.setOnCloseRequest((WindowEvent event) -> {
            currentUser.setStatus(Status.OFFLINE);
            updateUserList.stopRunning();
            if (CmdUtil.sendStatusChange(currentUser).equals(Boolean.TRUE)) {
                System.out.println("Odjavljivanje uspesno!");
                Platform.exit();
            } else {
                System.out.println("Nemoguce odjavljivanje!");
                Platform.exit();
            }
            event.consume();
        }
        );
    }

    /**
     * korisnik koji koristi pozornicu
     *
     * vraca korisnika pozornice
     */
    public static User getCurrentUser() {
        return MessengerStage.currentUser;
    }
    
}
