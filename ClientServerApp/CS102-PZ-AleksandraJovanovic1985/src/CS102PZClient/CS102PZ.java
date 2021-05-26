
package CS102PZClient;

import CS102PZCommon.Status;
import CS102PZCommon.User;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Aleksandra
 */
public class CS102PZ extends Application {
 /**
 * CS102PZ Main klasa je ona koja startuje aplikaciju
 *
 */
    public static Stage pStage;
    private User tempUser;
    
    @Override
    public void start(Stage primaryStage) {
        try {

            /**
             * Gui za prijavljivanje
             *
             */
            GridPane loginGrid = new GridPane();
            loginGrid.setVgap(6);
            loginGrid.setHgap(2);
            Label naslovLbl = new Label("CS102-PZ");
            Label usernameLbl = new Label("Korisnicko ime: ");
            Label passwordLbl = new Label("Lozinka: ");
            Label loginErrorLbl = new Label("");
            TextField usernameTF = new TextField();
            PasswordField passwordTF = new PasswordField();
            Button loginBtn = new Button("Prijavi se");
            Label signUpLbl = new Label("Nemas nalog? Registruj se!");
            signUpLbl.setStyle("-fx-text-fill: blue");
            loginGrid.add(naslovLbl, 0, 0, 5, 1);
            loginGrid.add(usernameLbl, 1, 1);
            loginGrid.add(usernameTF, 2, 1);
            loginGrid.add(passwordLbl, 1, 2);
            loginGrid.add(passwordTF, 2, 2);
            loginGrid.add(loginBtn, 2, 3);
            loginGrid.add(signUpLbl, 2, 4);
            loginGrid.add(loginErrorLbl, 1, 5);
            Scene loginScene = new Scene(loginGrid, 400, 250);

            /**
             * GUI za registraciju
             *
             */
            GridPane signUpGrid = new GridPane();
            signUpGrid.setVgap(10);
            signUpGrid.setHgap(2);

            Label signUpErrorLbl = new Label("");
            Label naslovSignUpLbl = new Label("CS102-PZ");
            Label firstNameLbl = new Label("Ime:");
            Label lastNameLbl = new Label("Prezime:");
            Label usernameSignUpLbl = new Label("Korisnicko ime:");
            Label passwordSignUpLbl = new Label("Lozinka:");
            Label confirmpassSignUpLbl = new Label("Potvrdi lozinku:");
            Label areYouHumanLbl = new Label("10+5");
            TextField firstNameTF = new TextField();
            TextField lastNameTF = new TextField();
            TextField usernameSignUpTF = new TextField();
            PasswordField passwordSignUpTF = new PasswordField();
            PasswordField confirmpassSignUpTF = new PasswordField();
            TextField areYouHumanTF = new TextField();
            Button signUpBtn = new Button("Registruj se");
            Label loginLbl = new Label("Postojeci korisnik? Prijavi se");
            loginLbl.setStyle("-fx-text-fill: blue");
            signUpGrid.add(naslovSignUpLbl, 0, 0, 5, 1);
            signUpGrid.add(firstNameLbl, 1, 1);
            signUpGrid.add(firstNameTF, 2, 1);
            signUpGrid.add(lastNameLbl, 1, 2);
            signUpGrid.add(lastNameTF, 2, 2);
            signUpGrid.add(usernameSignUpLbl, 1, 3);
            signUpGrid.add(usernameSignUpTF, 2, 3);
            signUpGrid.add(passwordSignUpLbl, 1, 4);
            signUpGrid.add(passwordSignUpTF, 2, 4);
            signUpGrid.add(confirmpassSignUpLbl, 1, 5);
            signUpGrid.add(confirmpassSignUpTF, 2, 5);
            signUpGrid.add(areYouHumanLbl, 1, 6);
            signUpGrid.add(areYouHumanTF, 2, 6);
            signUpGrid.add(signUpBtn, 2, 7);
            signUpGrid.add(loginLbl, 2, 8);
            signUpGrid.add(signUpErrorLbl, 2, 9);
            Scene signUpScene = new Scene(signUpGrid, 400, 400);
            primaryStage.setResizable(false);
            primaryStage.setTitle("CS102-PZ-AleksandraJovanovic1985");
            primaryStage.setScene(loginScene);
            primaryStage.show();
            pStage = primaryStage;
            /**
             * Logika za prijavljivanje
             */
            loginBtn.setOnAction((ActionEvent event) -> {
                tempUser = new User(usernameTF.getText(), passwordTF.getText());
                tempUser = CmdUtil.sendLogin(tempUser);
                if (tempUser != null) {
                    tempUser.setStatus(Status.ONLINE);
                    System.out.println("Uspesno prijavljivanje!");
                    MessengerStage messengerStage = new MessengerStage(tempUser);
                    pStage.close();
                } else {
                    System.out.println("Neuspesno prijavljivanje");
                    loginErrorLbl.setText("Neuspesno prijavljivanje");
                }
                event.consume();
            });
            signUpLbl.setOnMouseClicked((MouseEvent event) -> {
                pStage.setScene(signUpScene);
                event.consume();
            });
            /**
             * Logika za registraciju
             */
            signUpBtn.setOnAction((ActionEvent event) -> {
                if (passwordSignUpTF.getText().equals(confirmpassSignUpTF.getText()) && !"".equals(passwordSignUpTF.getText())) {
                    if (areYouHumanTF.getText().equals("15")) {
                        tempUser = new User(usernameSignUpTF.getText(), passwordSignUpTF.getText(), firstNameTF.getText(), lastNameTF.getText());
                        if (CmdUtil.sendRegistration(tempUser).equals(Boolean.TRUE)) {
                            pStage.setScene(loginScene);
                        } else if (CmdUtil.sendRegistration(tempUser).equals(Boolean.FALSE)) {
                            System.out.println("Korisnicko ime vec postoji");
                            signUpErrorLbl.setText("Korisnicko ime vec postoji");
                        }
                    } else {
                        System.out.println("Neuspesna verifikacija");
                        signUpErrorLbl.setText("Neuspesna verifikacija");
                    }
                } else {
                    System.out.println("Lozinke se ne podudaraju");
                    signUpErrorLbl.setText("Lozinke se ne podudaraju");

                }

                event.consume();
            });
            loginLbl.setOnMouseClicked((MouseEvent event) -> {
                pStage.setScene(loginScene);
                event.consume();
            });

            pStage.setOnCloseRequest((WindowEvent event) -> {
                Platform.exit();
                event.consume();
            });
        } catch (Exception e) {
            System.out.println("CS102PZ Exception");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
