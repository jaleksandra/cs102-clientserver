
package CS102PZClient;

import CS102PZCommon.Message;
import CS102PZCommon.User;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Aleksandra
 */
public class ChatWindow extends Stage {

    //ChatWindow klasa koristi se za kreiranje prozora za komunikaciju
    
    public static VBox porukeVBox;
    private ListMessagesThread listMessagesThread;
    
    /**
     * ChatWindow konstruktor
     *
     * korisnik koji prima poruke
    */
    
    public ChatWindow(User recipient) {
        listMessagesThread = new ListMessagesThread();
        porukeVBox = new VBox();
        Label username = new Label(recipient.getUsername());
        username.setStyle("-fx-background-color: #00bfff;");
        TextArea novaPorukaTA = new TextArea();
        novaPorukaTA.setMaxSize(300, 150);
        HBox novaPorukaHBox = new HBox();
        Button sendBtn = new Button("posalji");
        sendBtn.setMinSize(100, 150);
        BorderPane chatWindowLayout = new BorderPane();
        Scene scene = new Scene(chatWindowLayout, 400, 600);
        ScrollPane porukeScrollPane = new ScrollPane(porukeVBox);
        
        novaPorukaHBox.getChildren().addAll(novaPorukaTA, sendBtn);
        chatWindowLayout.setTop(username);
        chatWindowLayout.setCenter(porukeScrollPane);
        chatWindowLayout.setBottom(novaPorukaHBox);
        this.setScene(scene);
        this.setMinWidth(400);
        this.setMaxWidth(400);
        this.show();
        listMessagesThread.start();
        
        sendBtn.setOnAction(
                (ActionEvent event) -> {
                    Message tempMessage = new Message(MessengerStage.getCurrentUser().getUsername(), recipient.getUsername(), novaPorukaTA.getText());
                    CmdUtil.sendMessage(tempMessage);
                    novaPorukaTA.clear();
                }
        );
        
        this.setOnCloseRequest(
                (WindowEvent event) -> {
                    listMessagesThread.stopRunning();
                    this.close();
                }
        );
    }
    
}
