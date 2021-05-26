
package CS102PZServer;

/**
 *
 * @author Aleksandra
 */

import CS102PZCommon.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 
 * Serverska klasa obezbedjuje resenje koje omogucava rukovanje razlicitim poslovima na jednom
 * serverskom prikljucku
 */

public class Server {

    public static Socket socket;
    public static ObjectOutputStream outputStream;
    public static ObjectInputStream inputStream;
    public static ArrayList<InetAddress> connectedAddressess;

    private boolean running;
    private ServerSocket serverSocket;
    private Job jobID;

    /**
     * konstruktor klase Server 
     */
    public Server() {
        running = false;
    }

    /**
     * startServer je metod koji se koristi za pokretanje Server klase
     */
    public void startServer() {
        running = true;
        try {
            serverSocket = new ServerSocket(ServerSettings.getSERVER_PORT());
            System.out.println("Server osluskuje port :" + ServerSettings.getSERVER_PORT());

            while (running) {
                try {
                    socket = serverSocket.accept();
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    jobID = (Job) inputStream.readObject();
                    System.out.println("Primljen " + jobID.toString() + " posao.");
                    if (null != jobID) {
                        switch (jobID) {
                            case LOGIN:
                                new LoginService();
                                break;
                            case REGISTER:
                                new RegisterService();
                                break;
                            case MESSAGE:
                                new SendMessageService();
                                break;
                            case STATUS:
                                new StatusService();
                                break;
                            case LIST_USERS:
                                new ListUsersService();
                                break;
                            case GET_MESSAGES:
                                new ListMessagesService();
                                break;
                            default:
                                break;
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("IOException");
                }

            }
        } catch (IOException ex) {
            System.out.println("Server ne moze da osluskuje port : " + ServerSettings.getSERVER_PORT());
        }
    }

    /**
     * stopServer je metod koji se koristi za zaustavljanje Server klase
     */
    public void stopServer() {
        try {
            running = false;
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Neuspesno zaustavljanje servera");
        }
    }

}

