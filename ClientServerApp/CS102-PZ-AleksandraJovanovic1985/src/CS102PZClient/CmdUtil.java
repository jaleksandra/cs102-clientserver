
package CS102PZClient;

import CS102PZCommon.Job;
import CS102PZCommon.Message;
import CS102PZCommon.MessageList;
import CS102PZCommon.User;
import CS102PZCommon.UserList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * CmdUtil je staticka klasa koja omogucava slanje i primanje podataka
 * kroz mrezu
 */

/**
 *
 * @author Aleksandra
 */
public class CmdUtil {

    /**
     * Socket i tokovi
     */
    public static Socket socket;
    public static ObjectOutputStream outputStream;
    public static ObjectInputStream inputStream;
    public static ObjectOutputStream loginOutputStream;
    public static ObjectInputStream loginInputStream;

    /**
     * sendJob metoda salje posao do Servera
     *
     * @param job posao za slanje
     */
    private static void sendJob(Job job) {
        try {
            socket = new Socket(ClientSettings.getSERVER_HOSTNAME(), ClientSettings.getSERVER_PORT());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(job);
            outputStream.flush();
            System.out.println("Posalji " + job.toString());
        } catch (UnknownHostException e) {
            System.out.println("Neuspelo trazenje hosta");
        } catch (IOException ex) {
            System.out.println("Neuspesno slanje: " + job.toString());
        }
    }

    /**
     * sendLogin metoda rukuje podacima za prijavljivanje klijenta
     *
     * @param user podaci za prijavljivanje korisnika
     * @return user ili null
     */
    public static User sendLogin(User user) {
        User tempUser = user;
        try {
            sendJob(Job.LOGIN);
            outputStream.writeObject(tempUser);
            outputStream.flush();
            System.out.println("Slanje podataka za prijavljivanje za: " + user.getUsername());
            inputStream = new ObjectInputStream(socket.getInputStream());
            Boolean tempBoolean = (Boolean) inputStream.readObject();
            System.out.println("Primljeni podaci za prijavljivanje od: " + user.getUsername());
            if (Objects.equals(tempBoolean, Boolean.TRUE)) {
                return tempUser;
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            System.out.println("Nemoguce poslati podatke za prijavljivanje!");
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Nemoguce primiti podatke za prijavljivanje!");
            return null;
        }
    }

    /**
     * sendRegistration metoda rukuje podacima za registraciju klijenta
     *
     * @param user salje korisnicke podatke za registraciju
     * @return Boolean objekat koji govori da li je registracija uspela
     */
    public static Boolean sendRegistration(User user) {
        User tempUser = user;
        try {
            sendJob(Job.REGISTER);
            outputStream.writeObject(tempUser);
            outputStream.flush();
            System.out.println("Salje pokusaj registracije za korisnika: " + user.getUsername());
            inputStream = new ObjectInputStream(socket.getInputStream());
            Boolean tempBoolean = (Boolean) inputStream.readObject();
            if (Objects.equals(tempBoolean, Boolean.TRUE)) {
                System.out.println("Registracija uspela.");
                return Boolean.TRUE;
            } else {
                System.out.println("Korisnicko ime vec postoji.");
                return Boolean.FALSE;
            }
        } catch (NullPointerException e) {
            System.out.println("Nemoguce poslati podatke za registraciju!");
            return Boolean.FALSE;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Nemoguce primiti potvrdu o registraciji! ");
            return Boolean.FALSE;
        }
    }

    /**
     * sendStatusChange metoda rukuje promenom statusa na klijentu
     *
     * @param user salje korisnicke podatke za promenu statusa
     * @return Boolean objekat koji obavestava da li je promena statusa uspesna ili ne
     */
    public static Boolean sendStatusChange(User user) {

        User tempUser = user;
        try {
            sendJob(Job.STATUS);
            outputStream.writeObject(tempUser);
            outputStream.flush();
            System.out.println("Posalji " + user.getStatus().toString() + " pokusaj promene statusa.");
            inputStream = new ObjectInputStream(socket.getInputStream());
            Boolean tempBoolean = (Boolean) inputStream.readObject();
            if (Objects.equals(tempBoolean, Boolean.TRUE)) {
                System.out.println("Uspesno promenjen status.");
                return Boolean.TRUE;
            } else {
                System.out.println("Promena statusa neuspesna.");
                return Boolean.FALSE;
            }
        } catch (NullPointerException e) {
            System.out.println("Neuspesno slanje podataka za promenu statusa!");
            return Boolean.FALSE;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Nemoguce primiti podatke o statusu! ");
            return Boolean.FALSE;
        }
    }

    /**
     * sendMessage metoda koja rukuje slanjem poruka na klijentu
     *
     * @param message poruke koje su poslate
     * @return Boolean objekat koji saopstava da li je poruka uspesno poslata
     */
    public static Boolean sendMessage(Message message) {

        Message tempMessage = message;
        try {
            sendJob(Job.MESSAGE);
            outputStream.writeObject(tempMessage);
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            Boolean tempBoolean = (Boolean) inputStream.readObject();
            if (Objects.equals(tempBoolean, Boolean.TRUE)) {
                System.out.println("Poruka uspesno poslata");
                return Boolean.TRUE;
            } else {
                System.out.println("Neuspeli pokusaj slanja poruke");
                return Boolean.FALSE;
            }
        } catch (NullPointerException e) {
            System.out.println("Nemoguce poslati poruku!");
            return Boolean.FALSE;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Nemoguce primiti potvrdu o slanju poruke! ");
            return Boolean.FALSE;
        }
    }

    /**
     * sendListUsers metoda koja rukuje zahtevom za listom korisnika 
     *
     * @return listu korisnika od servera
     */
    public static UserList sendListUsers() {
        UserList tempUserList;
        try {
            sendJob(Job.LIST_USERS);
            inputStream = new ObjectInputStream(socket.getInputStream());
            tempUserList = (UserList) inputStream.readObject();
            System.out.println("Lista korisnika primljena");
            return tempUserList;
        } catch (NullPointerException e) {
            System.out.println("Nemoguce poslati listu korisnika!");
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Nemoguce primiti listu korisnika!");
            return null;
        }

    }

    /**
     * sendListMessages metoda salje zahtev za listom poruka
     *
     * @param user vlasnik liste poruka
     * @return listu poruka
     */
    public static MessageList sendListMessages(User user) {
        MessageList tempMessageList;
        try {
            sendJob(Job.GET_MESSAGES);
            outputStream.flush();
            outputStream.writeObject(user);
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            tempMessageList = (MessageList) inputStream.readObject();
            System.out.println("Primljene poruke");
            return tempMessageList;
        } catch (NullPointerException e) {
            System.out.println("Nemoguce poslati listu poruka!");
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Nemoguce primiti listu poruka.");
            return null;
        }
    }

    
}
